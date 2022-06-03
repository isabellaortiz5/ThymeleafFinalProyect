package com.sean.taller.businessdelegate.imp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sean.taller.businessdelegate.intfcs.ProductDelegate;
import com.sean.taller.model.prod.Product;

@Component
public class ProductDelegateImp implements ProductDelegate {
	private final static String URL = "http://localhost:8080/product/";

	private RestTemplate rt;

	@Override
	public Product save(Product p) {
		return rt.postForObject(URL, p, Product.class);
	}

	@Override
	public void update(Product p) {
		rt.put(URL + p.getProductid(), p);
	}

	@Override
	public void delete(Integer id) {
		rt.delete(URL + id, Integer.class);
	}

	@Override
	public Product findById(Integer id) {
		return rt.getForObject(URL + id, Product.class);
	}

	@Override
	public List<Product> findAll() {
		return Arrays.asList(rt.getForObject(URL, Product[].class));
	}
}
