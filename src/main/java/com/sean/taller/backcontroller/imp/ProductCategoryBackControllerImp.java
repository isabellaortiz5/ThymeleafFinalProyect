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

import com.sean.taller.backcontroller.intfcs.ProductCategoryBackController;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.services.intfcs.ProductcategoryService;

@RestController
@RequestMapping("product-category")
public class ProductCategoryBackControllerImp implements ProductCategoryBackController {

	@Autowired
	private ProductcategoryService ps;
	
	@Override
	@PostMapping("/")
	public Productcategory save(@RequestBody Productcategory pc) {
		return ps.save(pc);
	}

	@Override
	@PutMapping("/{id}")
	public Productcategory update(@PathVariable("id") Integer id, @RequestBody Productcategory pc) {
		return ps.edit(pc);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ps.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Productcategory findById(@PathVariable("id") Integer id) {
		return ps.findById(id);
	}

	@Override
	@GetMapping("/")
	public Iterable<Productcategory> findAll() {
		return ps.findAll();
	}

}
