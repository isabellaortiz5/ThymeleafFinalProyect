package com.sean.taller.backcontroller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.taller.backcontroller.intfcs.DepartmentBackController;
import com.sean.taller.model.hr.Department;
import com.sean.taller.services.intfcs.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentBackControllerImp implements DepartmentBackController {

	@Autowired
	private DepartmentService ds;
	
	@Override
	@PostMapping("/")
	public Department save(@RequestBody Department d) {
		return ds.save(d);
	}

	@Override
	@PutMapping("/{id}")
	public Department update(@PathVariable("id") Integer id, @RequestBody Department d) {
		return ds.edit(d);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ds.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Integer id) {
		return ds.findById(id);
	}

	@Override
	@GetMapping("/")
	public Iterable<Department> findAll() {
		return ds.findAll();
	}

}
