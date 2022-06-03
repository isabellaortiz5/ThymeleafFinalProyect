package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.prod.Productcategory;

public interface ProductCategoryBackController {
	public Productcategory save(Productcategory pc);
	public Productcategory update(Integer id, Productcategory pc);
	public void delete(Integer id);
	public Productcategory findById(Integer id);
	public Iterable<Productcategory> findAll();
}
