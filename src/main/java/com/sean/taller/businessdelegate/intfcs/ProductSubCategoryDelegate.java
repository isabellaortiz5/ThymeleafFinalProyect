package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Productsubcategory;

public interface ProductSubCategoryDelegate {
	public Productsubcategory save(Productsubcategory p);
	public void update(Productsubcategory p);
	public void delete(Integer id);
	public Productsubcategory findById(Integer id);
	public List<Productsubcategory> findAll();
}
