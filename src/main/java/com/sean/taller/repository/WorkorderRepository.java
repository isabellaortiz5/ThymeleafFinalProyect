package com.sean.taller.repository;

import org.springframework.data.repository.CrudRepository;

import com.sean.taller.model.prod.Workorder;

public interface WorkorderRepository extends CrudRepository<Workorder, Integer>{
	
}
