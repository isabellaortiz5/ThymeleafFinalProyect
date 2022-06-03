package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;
import com.sean.taller.model.hr.Department;

@Component
public class DepartmentDelegateImp implements DepartmentDelegate{
	private final static String URL = "http://localhost:8080/department/";

	private RestTemplate rt;

	@Override
	public Department save(Department d) {
		return rt.postForObject(URL, d, Department.class);
	}

	@Override
	public void update(Department d) {
		rt.put(URL + d.getDepartmentid(), d);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Department findById(Integer id) {
		return rt.getForObject(URL + id, Department.class);
	}

	@Override
	public List<Department> findAll() {
		return Arrays.asList(rt.getForObject(URL, Department[].class));
	}
}
