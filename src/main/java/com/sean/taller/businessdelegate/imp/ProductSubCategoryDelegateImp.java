package com.sean.taller.businessdelegate.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Productsubcategory;

@Component
public class ProductSubCategoryDelegateImp implements ProductSubCategoryDelegate{

	private final static String URL = "http://localhost:8080/api/product-sub-category/";

	private RestTemplate rt;
	
	public ProductSubCategoryDelegateImp() {
		this.rt = new RestTemplate();	
		List<HttpMessageConverter<?>> msgConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		msgConverters.add(converter);
		this.rt.setMessageConverters(msgConverters);
	}

	public RestTemplate getRt() {
		return rt;
	}

	public void setRt(RestTemplate rt) {
		this.rt = rt;
	}

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
	public Iterable<Productsubcategory> findAll() {
		Productsubcategory[] subcategs = rt.getForObject(URL, Productsubcategory[].class);
		System.out.println("ON DELEGATE LIST: " + subcategs[0].getProductcategory());
		return Arrays.asList(subcategs);
	}
}
