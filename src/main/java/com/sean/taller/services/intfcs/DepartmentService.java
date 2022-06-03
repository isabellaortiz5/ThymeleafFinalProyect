package com.sean.taller.services.intfcs;

import com.sean.taller.model.hr.Department;

public interface DepartmentService {
	public Department save(Department d);
	
	public Department edit(Department d);
	
	public void delete(Integer id);
	
	Iterable<Department> findAll();
	
	public Department findById(Integer id);

}
