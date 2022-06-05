package com.sean.taller.businessdelegate.intfcs;

import com.sean.taller.model.prod.Productsubcategory;

public interface ProductSubCategoryDelegate {
	public Productsubcategory save(Productsubcategory p);
	public void update(Productsubcategory p);
	public void delete(Integer id);
	public Productsubcategory findById(Integer id);
	public Iterable<Productsubcategory> findAll();
}
