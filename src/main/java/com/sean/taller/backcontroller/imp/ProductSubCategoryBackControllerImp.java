package com.sean.taller.backcontroller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sean.taller.backcontroller.intfcs.ProductSubCategoryBackController;
import com.sean.taller.dao.intfcs.ProductSubCategoryDao;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.services.intfcs.ProductsubcategoryService;

@RestController
@RequestMapping("/api/product-sub-category")
public class ProductSubCategoryBackControllerImp implements ProductSubCategoryBackController {

	@Autowired
	private ProductSubCategoryDao ps;
	
	public ProductSubCategoryBackControllerImp(ProductSubCategoryDao ps) {
		this.ps = ps;
	}
	
	@Override
	@PostMapping("/")
	public Productsubcategory save(@RequestBody Productsubcategory psc) {
		return ps.save(psc);
	}

	@Override
	@PutMapping("/{id}")
	public Productsubcategory update(@PathVariable("id") Integer id, @RequestBody Productsubcategory psc) {
		return ps.update(psc);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		ps.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public Productsubcategory findById(@PathVariable("id") Integer id) {
		return ps.findById(id);
	}

	@Override
	@GetMapping
	public Iterable<Productsubcategory> findAll() {
		Productsubcategory a = ps.findById(1);
		System.out.println("ON REST CONTROLLER-DAO: " + a.getProductcategory());
		return ps.findAll();
	}

	

}
