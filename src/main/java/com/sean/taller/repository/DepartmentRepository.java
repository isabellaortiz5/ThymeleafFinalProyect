package com.sean.taller.repository;

import org.springframework.data.repository.CrudRepository;

import com.sean.taller.model.hr.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
