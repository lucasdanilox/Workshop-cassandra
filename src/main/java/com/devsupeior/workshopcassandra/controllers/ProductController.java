package com.devsupeior.workshopcassandra.controllers;

import com.devsupeior.workshopcassandra.model.dto.ProductDTO;
import com.devsupeior.workshopcassandra.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID id) {
        ProductDTO result = service.findById(id);
        return ResponseEntity.ok().body(result);

    }
}
