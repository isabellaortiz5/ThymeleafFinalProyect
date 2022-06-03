package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Product;

public interface ProductDao {
	public Product save(Product p);
	public Product update(Product p);
	public void delete (Product p);
	public Product findById(Integer pId);
	public List<Product> findAll();
	public List<Product> findBySubCategory(Integer productsubcategoryid);
	public List<Product> findByUnitMeasure(Long unitmeasurecode);
}
