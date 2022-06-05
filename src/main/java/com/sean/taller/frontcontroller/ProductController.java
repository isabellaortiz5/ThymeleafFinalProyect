package com.sean.taller.frontcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.businessdelegate.intfcs.ProductDelegate;
import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Product;
import com.sean.taller.services.imp.UnitmeasureServiceImp;

@Controller
@RequestMapping("prod")
public class ProductController {
	private ProductDelegate pd;
	private ProductSubCategoryDelegate pscd;
	private UnitmeasureServiceImp ums;
	
	@Autowired
	public ProductController(ProductDelegate pd, ProductSubCategoryDelegate  pscd, UnitmeasureServiceImp  ums) {
		this.pd = pd;
		this.pscd = pscd;
		this.ums = ums;
	}

	//****************************** INDEX & INFO******************************
	@GetMapping("")
	public String index(Model model) {
		Iterable<Product> ip = pd.findAll();
		
		if(ip.iterator().hasNext()){
			model.addAttribute("products", ip);
		}
		
		return "prod/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") Integer id) {
		Product p = pd.findById(id);
		model.addAttribute("product",p);
		model.addAttribute("productsubcategories",pscd.findAll());
		model.addAttribute("unitmeasures",ums.findAll());
		return "prod/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @ModelAttribute Product p) {
		pd.update(p);
		return "redirect:/prod";
	}
	@GetMapping("/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productsubcategories", pscd.findAll());
		model.addAttribute("unitmeasures", ums.findAll());
		return "prod/add";
	}

	@PostMapping("/add")
	public String addProductPost(Model model, @ModelAttribute Product p) {
		pd.save(p);
		return "redirect:/prod";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(Model model, @PathVariable Integer id) {
		pd.delete(id);
		return "redirect:/prod";
	}
	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Product p = pd.findById(id);
		model.addAttribute("product",p);
		return "prod/information";
	}
}
