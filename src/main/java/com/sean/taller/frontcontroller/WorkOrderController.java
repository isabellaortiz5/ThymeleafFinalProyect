package com.sean.taller.frontcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.businessdelegate.intfcs.ProductDelegate;
import com.sean.taller.businessdelegate.intfcs.WorkOrderDelegate;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.repository.ScrapreasonRepository;
import com.sean.taller.services.intfcs.ProductService;
import com.sean.taller.services.intfcs.ScrapreasonService;

@Controller
@RequestMapping("work-ord")
public class WorkOrderController {
//	@Autowired
//	private WorkorderService wos;
	
	private ScrapreasonRepository sr;
	
	private ProductDelegate pd;

	private WorkOrderDelegate wod;
	
	@Autowired
	public WorkOrderController(WorkOrderDelegate wod, ProductDelegate pd, ScrapreasonRepository sr) {
		this.wod = wod;
		this.pd = pd;
		this.sr = sr;
	}
	
	//****************************** INDEX & INFO******************************
	@GetMapping("")
	public String index(Model model) {
		// WorkOrderDelegate --> wod
		Iterable<Workorder> iwo = wod.findAll();
		
		if(iwo.iterator().hasNext()){
			model.addAttribute("workorders", iwo);
			
		}
		
		return "work-ord/index";
	}
	
	@GetMapping("/{id}")
	public String getWorkOrder(Model model, @PathVariable Integer id) {
		Workorder wo = wod.findById(id);
		model.addAttribute("workorder", wo);
		
		return "work-ord/information";
	}
	
	//****************************** ADD ******************************
	@GetMapping("/add")
	public String addWorkOrder(Model model) {
		model.addAttribute("workorder", new Workorder());
		model.addAttribute("scrapreason", sr.findAll());
		model.addAttribute("product", pd.findAll());
		return "work-ord/add";
	}
	
	@PostMapping("/add")
	public String addWorkOrderPost(Model model, @ModelAttribute Workorder wo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {       
			model.addAttribute("workorder", new Workorder());
	        return "work-ord/add";
	    } else {
	    	System.out.println(wo.getOrderqty()+"q");
	    	wod.save(wo);
	    	return "redirect:/work-ord";
	    }
	}

	//****************************** EDIT ******************************
	@GetMapping("/edit/{id}")
	public String editWorkOrder(Model model, @PathVariable Integer id) {
		Workorder wo = wod.findById(id);
		if (wo == null)
			throw new IllegalArgumentException("Invalid workorder Id:" + id);
		
		model.addAttribute("workorder", wo);
		model.addAttribute("scrapreason", sr.findAll());
		model.addAttribute("product", pd.findAll());
		
		return "work-ord/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @PathVariable Integer id, @ModelAttribute Workorder wo) {
		wod.update(wo);
		return "redirect:/work-ord";
		
	}
	
	//****************************** DELETE ******************************
	@GetMapping("/delete/{id}")
	public String deleteWorkOrder(Model model, @PathVariable Integer id) {
		wod.delete(id);
		return "work-ord/index";
	}
	
	
}
