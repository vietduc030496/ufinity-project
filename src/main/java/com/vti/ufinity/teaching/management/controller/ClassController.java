package com.vti.ufinity.teaching.management.controller;

import com.vti.ufinity.teaching.management.controller.web.request.ClassRegisterRequest;
import com.vti.ufinity.teaching.management.model.dto.ClassDTO;
import com.vti.ufinity.teaching.management.service.ClassService;
import com.vti.ufinity.teaching.management.service.CrudService;
import jakarta.validation.Valid;
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
@RequestMapping("/class")
public class ClassController extends CrudController<ClassDTO>{

    private final ClassService classService;

    protected ClassController(ClassService crudService) {
        super(crudService);
        this.classService = crudService;
    }

    @PostMapping("/")
    public ResponseEntity<ClassDTO> save(@Valid @RequestBody ClassRegisterRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> update(@PathVariable Long id, @Valid @RequestBody ClassRegisterRequest body) {
        return ResponseEntity.ok(classService.update(id, body));
    }

}
