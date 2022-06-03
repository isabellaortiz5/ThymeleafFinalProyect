package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.hr.Department;

public interface DepartmentDao {
	public Department save(Department d);
	public Department update(Department d);
	void delete(Department d);
	public Department findById(Integer dId);
	public List<Department> findAll();
}
