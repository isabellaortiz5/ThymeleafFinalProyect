package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.WorkOrderDelegate;
import com.sean.taller.model.prod.Workorder;

@Component
public class WorkOrderDelegateImp implements WorkOrderDelegate {
	private final static String URL = "http://localhost:8080/api/work-ord/";

	private RestTemplate rt;
	
	public WorkOrderDelegateImp() {
		this.rt = new RestTemplate();
	}

	public RestTemplate getRestTemplate() {
		return rt;
	}
	
	@Override
	public Workorder save(Workorder w) {
		return rt.postForObject(URL, w, Workorder.class);
	}

	@Override
	public void update(Workorder w) {
		rt.put(URL + w.getWorkorderid(), w);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Workorder findById(Integer id) {
		return rt.getForObject(URL + id, Workorder.class);
	}

	@Override
	public List<Workorder> findAll() {
		return Arrays.asList(rt.getForObject(URL, Workorder[].class));
	}
}
