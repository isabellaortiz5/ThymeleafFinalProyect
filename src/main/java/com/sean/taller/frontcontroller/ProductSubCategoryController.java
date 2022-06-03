package com.sean.taller.frontcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.services.intfcs.ProductService;
import com.sean.taller.services.intfcs.ProductcategoryService;
import com.sean.taller.services.intfcs.ProductsubcategoryService;

@Controller
@RequestMapping("prod-sub-categ")
public class ProductSubCategoryController {
	@Autowired
	private ProductsubcategoryService pscs;
	@Autowired
	private ProductcategoryService pcs;
	
	@Autowired
	public ProductSubCategoryController(ProductsubcategoryService pscs, ProductcategoryService pcs,ProductService ps) {
		this.pscs = pscs;
		this.pcs = pcs;
	}

	@GetMapping("")
	public String index(Model model) {
		Iterable<Productsubcategory> psc = pscs.findAll();
		
		if(psc.iterator().hasNext()){
			model.addAttribute("productsubcategs", psc);
		}
		return "/prod-sub-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {
		Productsubcategory psc = pscs.findById(id);
		if (psc == null)
			throw new IllegalArgumentException("Invalid Product categ Id:" + id);
		
		model.addAttribute("productsubcateg", psc);
		model.addAttribute("productcategs", pcs.findAll());
		return "prod-sub-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @ModelAttribute Productsubcategory psc) {
		pscs.edit(psc);
		return "redirect:/prod-sub-categ";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		model.addAttribute("productsubcateg", new Productsubcategory());
		model.addAttribute("productcategs", pcs.findAll());
		return "prod-sub-categ/add";
	}

	@PostMapping("/add")
	public String addProductSubCategoryPost(Model model, @Valid @ModelAttribute Productsubcategory psc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {       
			model.addAttribute("productsubcateg", new Productcategory());
	        return "prod-sub-categ/add";
	    } else {
	    	pscs.save(psc);
	    	return "redirect:/prod-sub-categ";
	    }
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model, @PathVariable Integer id) {
		pscs.delete(id);
		return "redirect:/prod-sub-categ";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model,@PathVariable("id") Integer id) {
		Productsubcategory psc = pscs.findById(id);
		model.addAttribute("productsubcateg", psc);
		return "prod-sub-categ/information";
	}
}
