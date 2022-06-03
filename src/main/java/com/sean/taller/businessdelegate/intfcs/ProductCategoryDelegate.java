package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Productcategory;


public interface ProductCategoryDelegate {
	public Productcategory save(Productcategory p);
	public void update(Productcategory p);
	public void delete(Integer id);
	public Productcategory findById(Integer id);
	public List<Productcategory> findAll();
}
