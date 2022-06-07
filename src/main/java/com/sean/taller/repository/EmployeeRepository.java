package com.sean.taller.repository;
import org.springframework.data.repository.CrudRepository;
import com.sean.taller.model.hr.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
