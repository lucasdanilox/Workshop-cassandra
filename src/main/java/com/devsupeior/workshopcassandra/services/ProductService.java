package com.devsupeior.workshopcassandra.services;

import com.devsupeior.workshopcassandra.model.dto.ProductDTO;
import com.devsupeior.workshopcassandra.model.entities.Product;
import com.devsupeior.workshopcassandra.repositories.ProductRepository;
import com.devsupeior.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(UUID id) {
        Product entity = getById(id);
        return new ProductDTO(entity);

    }

    public List<ProductDTO> findByDepartment(String department) {
        List<Product> list;
        if ("".equals(department)) {
            list = repository.findAll();
        } else {
            list = repository.findByDepartment(department);
        }
        return list.stream().map(ProductDTO::new).collect(Collectors.toList());
    }


    private Product getById(UUID id) {
        Optional<Product> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }

}
