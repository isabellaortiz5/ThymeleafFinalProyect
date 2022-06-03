package com.sean.taller.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.repository.UnitmeasureRepository;
import com.sean.taller.services.intfcs.UnitmeasureService;

@Service
@Transactional
public class UnitmeasureServiceImp implements UnitmeasureService {
	private UnitmeasureRepository umr;

	@Autowired
	public UnitmeasureServiceImp(UnitmeasureRepository umr) {
		this.umr = umr;
	}
	
	public Iterable<Unitmeasure> findAll() {
		return umr.findAll();
	}
	
	@Override
	public Unitmeasure save(Unitmeasure vendor) {
		// TODO Auto-generated method stub
		return umr.save(vendor);
	}

	
}
