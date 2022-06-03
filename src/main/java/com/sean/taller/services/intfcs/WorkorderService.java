package com.sean.taller.services.intfcs;
import com.sean.taller.model.prod.Workorder;

public interface WorkorderService {
	public Workorder add(Workorder wo);

	public Workorder edit(Workorder wo);
	
	public void delete(Integer id);
	
	Iterable<Workorder> findAll();

	public Workorder findById(Integer id);
}
