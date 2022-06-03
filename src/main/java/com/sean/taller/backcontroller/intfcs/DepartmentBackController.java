package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.hr.Department;

public interface DepartmentBackController {
	public Department save(Department d);
	public Department update(Integer id, Department d);
	public void delete(Integer id);
	public Department findById(Integer id);
	public Iterable<Department> findAll();
}
