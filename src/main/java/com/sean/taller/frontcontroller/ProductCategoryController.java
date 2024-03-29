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

import com.sean.taller.businessdelegate.intfcs.ProductCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;

@Controller
@RequestMapping("prod-categ")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryDelegate pcd;
	
	@Autowired
	public ProductCategoryController(ProductCategoryDelegate pcd) {
		this.pcd = pcd;

	}

	@GetMapping("")
	public String index(Model model) {
		
		Iterable<Productcategory> pcss = pcd.findAll();
		
		if(pcss.iterator().hasNext()){
			model.addAttribute("productcategories",pcss);
		}
		
		return "/prod-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductCategory(Model model, @PathVariable("id") Integer id) {
		Productcategory pc = pcd.findById(id);
		if (pc == null)
			throw new IllegalArgumentException("Invalid Prosuct categ Id:" + id);
		
		model.addAttribute("productcategory", pc);
		
		return "prod-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProductCategory(Model model, @ModelAttribute Productcategory pc) {
		pcd.update(pc);
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
	    	pcd.save(pc);
	    	return "redirect:/prod-categ";
	    }
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductCategory(Model model,  @PathVariable Integer id) {
		pcd.delete(id);
		return "redirect:/prod-categ";
	}
	@GetMapping("/{id}")
	public String getProductCategory(Model model, @PathVariable Integer id) {
		Productcategory pc = pcd.findById(id);
		model.addAttribute("productcategory", pc);
		return "prod-categ/information";
	}
}
