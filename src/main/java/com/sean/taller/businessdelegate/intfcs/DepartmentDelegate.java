package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.hr.Department;

public interface DepartmentDelegate {
	public Department save(Department d);
	public void update(Department d);
	public void delete(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
}
