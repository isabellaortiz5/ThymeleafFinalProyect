package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Product;

public interface ProductDelegate {
	public Product save(Product p);
	public void update(Product p);
	public void delete(Integer id);
	public Product findById(Integer id);
	public List<Product> findAll();
}
