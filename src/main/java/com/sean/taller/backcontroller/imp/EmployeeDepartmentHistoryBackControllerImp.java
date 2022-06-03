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

import com.sean.taller.backcontroller.intfcs.EmployeeDepartmentHistoryBackController;
import com.sean.taller.model.hr.Employeedepartmenthistory;
import com.sean.taller.services.intfcs.EmployeeDepartmentHistoryService;

@RestController
@RequestMapping("/employee-department-history")
public class EmployeeDepartmentHistoryBackControllerImp implements EmployeeDepartmentHistoryBackController {

	@Autowired
	private EmployeeDepartmentHistoryService es;
	
	@Override
	@PostMapping("/")
	public Employeedepartmenthistory save(@RequestBody Employeedepartmenthistory e) {
		return es.save(e);
	}

	@Override
	@PutMapping("/{id}")
	public Employeedepartmenthistory update(@PathVariable("id") Integer id, @RequestBody Employeedepartmenthistory e) {
		return es.edit(e);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		es.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Employeedepartmenthistory findById(@PathVariable("id") Integer id) {
		return es.findById(id);
	}

	@Override
	@GetMapping("/")
	public Iterable<Employeedepartmenthistory> findAll() {
		return es.findAll();
	}

}
