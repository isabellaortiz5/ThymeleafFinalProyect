package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.sean.taller.model.prod.Product;

public interface ProductDelegate {
	public RestTemplate getRt();
	public void setRt(RestTemplate rt);
	public Product save(Product p);
	public void update(Product p);
	public void delete(Integer id);
	public Product findById(Integer id);
	public List<Product> findAll();
}
