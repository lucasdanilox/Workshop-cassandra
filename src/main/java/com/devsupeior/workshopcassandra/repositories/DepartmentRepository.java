package com.devsupeior.workshopcassandra.repositories;

import com.devsupeior.workshopcassandra.model.entities.Department;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface DepartmentRepository extends CassandraRepository<Department, UUID> {
}
