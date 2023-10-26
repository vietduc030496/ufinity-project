package com.vti.ufinity.teaching.management.controller;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterRequest;
import com.vti.ufinity.teaching.management.model.dto.StudentDTO;
import com.vti.ufinity.teaching.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@RestController
@RequestMapping("/students")
public class StudentController extends CrudController<StudentDTO> {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService crudService) {
        super(crudService);
        this.studentService = crudService;
    }

    @PostMapping("/")
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentRegisterRequest body){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @Valid @RequestBody StudentRegisterRequest body){
        return ResponseEntity.ok(studentService.update(id, body));
    }

    @PatchMapping("/{id}/class/{classId}/enroll")
    public ResponseEntity<String> enrollClass(@PathVariable Long id, @PathVariable Long classId){
        return ResponseEntity.ok(studentService.enrollClass(id, classId));
    }

    @PatchMapping("/{id}/class/{classId}/deregister")
    public ResponseEntity<String> deregisterClass(@PathVariable Long id, @PathVariable Long classId){
        return ResponseEntity.ok(studentService.deregisterClass(id, classId));
    }

}
