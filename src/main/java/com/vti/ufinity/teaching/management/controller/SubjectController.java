package com.vti.ufinity.teaching.management.controller;

import com.vti.ufinity.teaching.management.controller.web.request.SubjectRegisterRequest;
import com.vti.ufinity.teaching.management.model.dto.SubjectDTO;
import com.vti.ufinity.teaching.management.service.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController extends CrudController<SubjectDTO> {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService crudService) {
        super(crudService);
        this.subjectService = crudService;
    }

    @PostMapping("/")
    public ResponseEntity<SubjectDTO> save(@Valid @RequestBody SubjectRegisterRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> update(@PathVariable Long id, @Valid @RequestBody SubjectRegisterRequest body) {
        return ResponseEntity.ok(subjectService.update(id, body));
    }

}
