package com.sean.taller.frontcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sean.taller.model.prod.Product;
import com.sean.taller.services.imp.ProductsubcategoryServiceImp;
import com.sean.taller.services.imp.UnitmeasureServiceImp;
import com.sean.taller.services.intfcs.ProductService;

@Controller
@RequestMapping("prod")
public class ProductController {
	private ProductService ps;
	private ProductsubcategoryServiceImp pscs;
	private UnitmeasureServiceImp ums;
	
	@Autowired
	public ProductController(ProductService ps, ProductsubcategoryServiceImp  pscs, UnitmeasureServiceImp  ums) {
		this.ps = ps;
		this.pscs = pscs;
		this.ums = ums;
	}

	//****************************** INDEX & INFO******************************
	@GetMapping("")
	public String index(Model model) {
		Iterable<Product> ip = ps.findAll();
		
		if(ip.iterator().hasNext()){
			model.addAttribute("products", ip);
		}
		
		return "prod/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProduct(Model model, @PathVariable("id") Integer id) {
		Product p = ps.findById(id);
		model.addAttribute("product",p);
		model.addAttribute("productsubcategories",pscs.findAll());
		model.addAttribute("unitmeasures",ums.findAll());
		return "prod/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @ModelAttribute Product p) {
		ps.edit(p);
		return "redirect:/prod";
	}
	@GetMapping("/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productsubcategories", pscs.findAll());
		model.addAttribute("unitmeasures", ums.findAll());
		return "prod/add";
	}

	@PostMapping("/add")
	public String addProductPost(Model model, @ModelAttribute Product p) {
		ps.save(p);
		return "redirect:/prod";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(Model model, @PathVariable Integer id) {
		ps.delete(id);
		return "redirect:/prod";
	}
	@GetMapping("/{id}")
	public String getProduct(Model model, @PathVariable("id") Integer id) {
		Product p = ps.findById(id);
		model.addAttribute("product",p);
		return "prod/information";
	}
}
