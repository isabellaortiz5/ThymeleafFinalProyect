package com.sean.taller.services.imp;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.model.prod.Scrapreason;
import com.sean.taller.repository.ScrapreasonRepository;
import com.sean.taller.services.intfcs.ScrapreasonService;

@Service
@Transactional
public class ScrapreasonServiceImp implements ScrapreasonService{
	private ScrapreasonRepository srr;
	
	public ScrapreasonServiceImp(ScrapreasonRepository srr) {
		this.srr = srr;
	}
	
	public Iterable<Scrapreason> findAll() {
		return srr.findAll();
	}
	
	@Override
	public Scrapreason save(Scrapreason sr) {
		return srr.save(sr);
	}

}
