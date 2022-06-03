package com.sean.taller.repository;


import org.springframework.data.repository.CrudRepository;

import com.sean.taller.model.prod.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	


}
