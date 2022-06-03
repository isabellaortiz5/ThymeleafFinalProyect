package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.prod.Workorder;

public interface WorkOrderBackController {
	public Workorder save(Workorder w);
	public Workorder update(Integer id, Workorder w);
	public void delete(Integer id);
	public Workorder findById(Integer id);
	public Iterable<Workorder> findAll();
}
