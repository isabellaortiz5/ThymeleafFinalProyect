package com.sean.taller.businessdelegate.intfcs;

import org.springframework.web.client.RestTemplate;

import com.sean.taller.model.prod.Productsubcategory;

public interface ProductSubCategoryDelegate {
	public RestTemplate getRt();
	public void setRt(RestTemplate rt);
	public Productsubcategory save(Productsubcategory p);
	public void update(Productsubcategory p);
	public void delete(Integer id);
	public Productsubcategory findById(Integer id);
	public Iterable<Productsubcategory> findAll();
}
