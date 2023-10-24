package com.vti.ufinity.teaching.management.controller;

import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import com.vti.ufinity.teaching.management.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController extends CrudController<TeacherDTO> {

    @Autowired
    public TeacherController(CrudService<TeacherDTO> crudService) {
        super(crudService);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("aaaaaaaaa");
    }

}
