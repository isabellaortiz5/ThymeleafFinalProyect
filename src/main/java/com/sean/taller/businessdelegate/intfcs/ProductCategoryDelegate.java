package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.sean.taller.model.prod.Productcategory;


public interface ProductCategoryDelegate {
	public RestTemplate getRt();
	public void setRt(RestTemplate rt);
	public Productcategory save(Productcategory p);
	public void update(Productcategory p);
	public void delete(Integer id);
	public Productcategory findById(Integer id);
	public List<Productcategory> findAll();
}
