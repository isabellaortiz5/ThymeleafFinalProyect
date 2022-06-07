package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.EmployeeDepartmentHistoryDelegate;
import com.sean.taller.model.hr.Employeedepartmenthistory;

@Component
public class EmployeeDepartmentHistoryDelegateImp implements EmployeeDepartmentHistoryDelegate {
	private final static String URL = "http://localhost:8080/api/employee-department-history/";

	private RestTemplate rt;
	
	public EmployeeDepartmentHistoryDelegateImp() {
		this.rt = new RestTemplate();	
	}
	
	@Override
	public Employeedepartmenthistory save(Employeedepartmenthistory e) {
		return rt.postForObject(URL, e, Employeedepartmenthistory.class);
	}

	@Override
	public void update(Employeedepartmenthistory e) {
		rt.put(URL + e.getId(), e);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Employeedepartmenthistory findById(Integer id) {
		return rt.getForObject(URL + id, Employeedepartmenthistory.class);
	}

	@Override
	public List<Employeedepartmenthistory> findAll() {
		return Arrays.asList(rt.getForObject(URL, Employeedepartmenthistory[].class));
	}
}
