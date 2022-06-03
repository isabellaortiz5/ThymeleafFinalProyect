package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.prod.Productsubcategory;

public interface ProductSubCategoryBackController {
	public Productsubcategory save(Productsubcategory psc);
	public Productsubcategory update(Integer id, Productsubcategory psc);
	public void delete(Integer id);
	public Productsubcategory findById(Integer id);
	public Iterable<Productsubcategory> findAll();
}
