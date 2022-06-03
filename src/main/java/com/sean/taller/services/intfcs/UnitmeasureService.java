package com.sean.taller.services.intfcs;
import com.sean.taller.model.prod.Unitmeasure;

public interface UnitmeasureService {
	public Unitmeasure save(Unitmeasure vendor);
	public Iterable<Unitmeasure> findAll();	
}
