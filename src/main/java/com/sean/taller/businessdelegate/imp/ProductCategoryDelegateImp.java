package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.ProductCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;

@Component
public class ProductCategoryDelegateImp implements ProductCategoryDelegate {

	private final static String URL = "http://localhost:8080/api/product-category/";

	private RestTemplate rt;

	public RestTemplate getRt() {
		return rt;
	}

	public void setRt(RestTemplate rt) {
		this.rt = rt;
	}

	public ProductCategoryDelegateImp() {
		this.rt = new RestTemplate();	
		}
	
	@Override
	public Productcategory save(Productcategory p) {
		return rt.postForObject(URL, p, Productcategory.class);
	}

	@Override
	public void update(Productcategory p) {
		rt.put(URL + p.getProductcategoryid(), p);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Productcategory findById(Integer id) {
		return rt.getForObject(URL + id, Productcategory.class);
	}

	@Override
	public List<Productcategory> findAll() {
		return Arrays.asList(rt.getForObject(URL, Productcategory[].class));
	}
}
