package com.vti.ufinity.teaching.management.controller;

import java.util.List;
import java.util.Optional;

import com.vti.ufinity.teaching.management.model.dto.BaseDTO;
import com.vti.ufinity.teaching.management.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public abstract class CrudController<T extends BaseDTO> {

    private final CrudService<T> service;

    protected CrudController(CrudService<T> crudService) {
        this.service = crudService;
    }

    @GetMapping("/")
//    @ApiOperation(value = "List all")
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @ApiOperation(value = "Get by Id")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        Optional<T> optionalT = service.findById(id);
        return optionalT.map(t ->
                                 new ResponseEntity<>(t, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete by Id")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<T> optional = service.findById(id);
        return optional.map(t -> {
                           service.delete(id);
                           return new ResponseEntity<>("Object with the id " + id + " was deleted.",
                                                       HttpStatus.OK);
                       })
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }

}
