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
import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.services.intfcs.ProductService;

@Controller
@RequestMapping("prod-sub-categ")
public class ProductSubCategoryController {
	@Autowired
	private ProductSubCategoryDelegate pscd;
	@Autowired
	private ProductCategoryDelegate pcd;
	
	@Autowired
	public ProductSubCategoryController(ProductSubCategoryDelegate pscd, ProductCategoryDelegate pcd,ProductService ps) {
		this.pscd = pscd;
		this.pcd = pcd;
	}
	
	//@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@GetMapping
	public String index(Model model) {
		Iterable<Productsubcategory> psc = pscd.findAll();
		
		if(psc.iterator().hasNext()){
			Productsubcategory a = psc.iterator().next();
			System.out.println("ON FRONT CONTROLLER - DELEGATE: " + a.getProductcategory());
			model.addAttribute("productsubcategs", psc);
		}
		return "prod-sub-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {
		Productsubcategory psc = pscd.findById(id);
		if (psc == null)
			throw new IllegalArgumentException("Invalid Product categ Id:" + id);
		
		model.addAttribute("productsubcateg", psc);
		model.addAttribute("productcategs", pcd.findAll());
		return "prod-sub-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @ModelAttribute Productsubcategory psc) {
		pscd.update(psc);
		return "redirect:/prod-sub-categ";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		model.addAttribute("productsubcateg", new Productsubcategory());
		model.addAttribute("productcategs", pcd.findAll());
		return "prod-sub-categ/add";
	}

	@PostMapping("/add")
	public String addProductSubCategoryPost(Model model, @Valid @ModelAttribute Productsubcategory psc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {       
			model.addAttribute("productsubcateg", new Productcategory());
	        return "prod-sub-categ/add";
	    } else {
	    	pscd.save(psc);
	    	return "redirect:/prod-sub-categ";
	    }
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model, @PathVariable Integer id) {
		pscd.delete(id);
		return "redirect:/prod-sub-categ";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model,@PathVariable("id") Integer id) {
		Productsubcategory psc = pscd.findById(id);
		model.addAttribute("productsubcateg", psc);
		return "prod-sub-categ/information";
	}
}
