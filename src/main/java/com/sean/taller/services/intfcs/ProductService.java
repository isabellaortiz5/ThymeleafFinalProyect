package com.sean.taller.services.intfcs;

import com.sean.taller.model.prod.Product;

public interface ProductService {
	public Product save(Product p);
	
	public Product edit(Product p);
	
	public void delete(Integer id);
	
	Iterable<Product> findAll();
	
	public Product findById(Integer id);
}
