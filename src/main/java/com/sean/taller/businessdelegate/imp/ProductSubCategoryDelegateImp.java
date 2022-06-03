package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Productsubcategory;

@Component
public class ProductSubCategoryDelegateImp implements ProductSubCategoryDelegate{

	private final static String URL = "http://localhost:8080/product-sub-category/";

	private RestTemplate rt;

	@Override
	public Productsubcategory save(Productsubcategory p) {
		return rt.postForObject(URL, p, Productsubcategory.class);
	}

	@Override
	public void update(Productsubcategory p) {
		rt.put(URL + p.getProductsubcategoryid(), p);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Productsubcategory findById(Integer id) {
		return rt.getForObject(URL + id, Productsubcategory.class);
	}

	@Override
	public List<Productsubcategory> findAll() {
		return Arrays.asList(rt.getForObject(URL, Productsubcategory[].class));
	}
}
