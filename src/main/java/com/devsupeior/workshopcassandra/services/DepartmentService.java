package com.devsupeior.workshopcassandra.services;

import com.devsupeior.workshopcassandra.model.dto.DepartmentDTO;
import com.devsupeior.workshopcassandra.model.entities.Department;
import com.devsupeior.workshopcassandra.repositories.DepartmentRepository;
import com.devsupeior.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentDTO> findAll() {
        List<Department> list = repository.findAll();
        return list.stream().map(DepartmentDTO::new).collect(Collectors.toList());
    }

    public DepartmentDTO findById(UUID id) {
        Department entity = getById(id);
        return new DepartmentDTO(entity);
    }

    public DepartmentDTO insert(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setId(UUID.randomUUID());
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new DepartmentDTO(entity);
    }

    public DepartmentDTO update(UUID id, DepartmentDTO dto) {
        Department entity = getById(id);
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new DepartmentDTO(entity);
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("id not found");
        }
        repository.deleteById(id);
    }


    private void copyDtoToEntity(Department entity, DepartmentDTO dto) {
        entity.setName(dto.getName());
    }

    private Department getById(UUID id) {
        Optional<Department> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }

}
