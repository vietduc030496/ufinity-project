package com.vti.ufinity.teaching.management.controller;

import com.vti.ufinity.teaching.management.controller.web.request.TeacherRegisterRequest;
import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import com.vti.ufinity.teaching.management.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/teachers")
public class TeacherController extends CrudController<TeacherDTO> {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService crudService) {
        super(crudService);
        this.teacherService = crudService;
    }

    @PostMapping("/")
    public ResponseEntity<TeacherDTO> save(@Valid @RequestBody TeacherRegisterRequest body){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @Valid @RequestBody TeacherRegisterRequest body){
        return ResponseEntity.ok(teacherService.update(id, body));
    }

}
