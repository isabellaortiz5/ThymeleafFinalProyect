package com.sean.taller.services.intfcs;

import com.sean.taller.model.prod.Productsubcategory;

public interface ProductsubcategoryService {
	public Productsubcategory save(Productsubcategory psc);
	public Productsubcategory edit(Productsubcategory psc);
	public void delete(Integer id);
	public Iterable<Productsubcategory> findAll();
	public Productsubcategory findById(Integer id);
}
