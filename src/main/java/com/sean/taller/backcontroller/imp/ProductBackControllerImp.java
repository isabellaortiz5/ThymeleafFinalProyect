package com.sean.taller.backcontroller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sean.taller.backcontroller.intfcs.ProductBackController;
import com.sean.taller.model.prod.Product;
import com.sean.taller.services.intfcs.ProductService;

@RestController
@RequestMapping("product")
public class ProductBackControllerImp implements ProductBackController {

	@Autowired
	private ProductService ps;
	
	@Override
	@PostMapping("/")
	public Product save(@RequestBody Product p) {
		return ps.save(p);
	}

	@Override
	@PutMapping("/{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product p) {
		return ps.edit(p);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ps.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Integer id) {
		return ps.findById(id);
	}

	@Override
	@GetMapping("/")
	public Iterable<Product> findAll() {
		return ps.findAll();
	}

}
