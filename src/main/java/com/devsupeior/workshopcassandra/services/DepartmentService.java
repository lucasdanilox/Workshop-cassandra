package com.devsupeior.workshopcassandra.services;

import com.devsupeior.workshopcassandra.model.dto.DepartmentDTO;
import com.devsupeior.workshopcassandra.model.entities.Department;
import com.devsupeior.workshopcassandra.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentDTO> findAll() {
        List<Department> list = repository.findAll();
        return list.stream().map(DepartmentDTO::new).collect(Collectors.toList());
    }
}
