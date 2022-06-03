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
import com.sean.taller.services.intfcs.ProductcategoryService;

@Controller
@RequestMapping("prod-categ")
public class ProductCategoryController {
	
	@Autowired
	private ProductcategoryService pcs;
	
	@Autowired
	public ProductCategoryController(ProductcategoryService pcs) {
		this.pcs = pcs;

	}

	@GetMapping("")
	public String index(Model model) {
		
		Iterable<Productcategory> pcss = pcs.findAll();
		
		if(pcss.iterator().hasNext()){
			model.addAttribute("productcategories",pcss);
		}
		
		return "/prod-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductCategory(Model model, @PathVariable("id") Integer id) {
		Productcategory pc = pcs.findById(id);
		if (pc == null)
			throw new IllegalArgumentException("Invalid Prosuct categ Id:" + id);
		
		model.addAttribute("productcategory", pc);
		
		return "prod-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProductCategory(Model model, @ModelAttribute Productcategory pc) {
		pcs.edit(pc);
		return "redirect:/prod-categ";
	}
	
	@GetMapping("/add")
	public String addProductCategory(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "prod-categ/add";
	}

	@PostMapping("/add")
	public String addProductCategoryPost(Model model, @Valid @ModelAttribute Productcategory pc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {       
			model.addAttribute("productcategory", new Productcategory());
	        return "prod-categ/add";
	    } else {
	    	pcs.save(pc);
	    	return "redirect:/prod-categ";
	    }
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductCategory(Model model,  @PathVariable Integer id) {
		pcs.delete(id);
		return "redirect:/prod-categ";
	}
	@GetMapping("/{id}")
	public String getProductCategory(Model model, @PathVariable Integer id) {
		Productcategory pc = pcs.findById(id);
		model.addAttribute("productcategory", pc);
		return "prod-categ/information";
	}
}
