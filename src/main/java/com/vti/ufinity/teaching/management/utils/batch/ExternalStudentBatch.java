package com.vti.ufinity.teaching.management.utils.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterExternalRequest;
import com.vti.ufinity.teaching.management.model.Student;
import com.vti.ufinity.teaching.management.model.mapper.StudentMapper;
import com.vti.ufinity.teaching.management.repository.StudentRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalStudentBatch {

    private final StudentRepository studentRepository;

    /**
     * Run when 12 am daily
     *
     * @throws IOException
     */
    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    public void insertExternalStudent() throws IOException {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        Set<String> allStudentEmails = studentRepository.findAllEmails();
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {

            Validator validator = factory.getValidator();

            File file = ResourceUtils.getFile("classpath:csv/external-student.csv");
            inputStream = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            List<StudentRegisterExternalRequest> studentExternals = new ArrayList<>();
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                StudentRegisterExternalRequest request = verify(line, validator, allStudentEmails, row);

                if (request != null) {

                    studentExternals.add(request);
                }

                ++row;
            }

            List<Student> students = studentExternals.stream().map(StudentMapper.INSTANCE::externalToEntity).toList();
            studentRepository.saveAll(students);
        } catch (IOException e) {

            log.error("An error has occurred when insert external student.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private StudentRegisterExternalRequest verify(String line, Validator validator, Set<String> allStudentEmails,
                                                  int row) {
        final String[] column = line.split(",");
        if (column.length < 4) {

            log.error("Row {} : Please complete input data.", row);
            return null;
        }

        final StudentRegisterExternalRequest request = new StudentRegisterExternalRequest();

        int columnIdx = 0;
        request.setFirstName(column[columnIdx++].strip());
        request.setLastName(column[columnIdx++].strip());
        request.setEmail(column[columnIdx++].strip());
        request.setDateOfBirth(column[columnIdx].strip());

        Set<ConstraintViolation<StudentRegisterExternalRequest>> validate = validator.validate(request);
        if (!validate.isEmpty() || allStudentEmails.contains(request.getEmail())) {
            validate.forEach(e -> log.error("row {} : {}", row, e.getMessage()));
            if (allStudentEmails.contains(request.getEmail())) {
                log.error("Row {} : email [{}] is used by other student.", row, request.getEmail());
            }
            return null;
        }
        return request;
    }

}
