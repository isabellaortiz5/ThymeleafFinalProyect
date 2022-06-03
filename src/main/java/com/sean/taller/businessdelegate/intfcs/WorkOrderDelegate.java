package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Workorder;

public interface WorkOrderDelegate {
	public Workorder save(Workorder w);
	public void update(Workorder w);
	public void delete(Integer id);
	public Workorder findById(Integer id);
	public List<Workorder> findAll();
}
