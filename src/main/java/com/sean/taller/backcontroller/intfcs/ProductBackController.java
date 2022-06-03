package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.prod.Product;

public interface ProductBackController {
	public Product save(Product p);
	public Product update(Integer id, Product p);
	public void delete(Integer id);
	public Product findById(Integer id);
	public Iterable<Product> findAll();
}
