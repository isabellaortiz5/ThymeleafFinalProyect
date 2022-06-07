package com.sean.taller.frontcontroller;

import java.util.List;

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

import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;
import com.sean.taller.businessdelegate.intfcs.EmployeeDepartmentHistoryDelegate;
import com.sean.taller.model.hr.Employeedepartmenthistory;
import com.sean.taller.services.imp.EmployeeServiceImp;

@Controller
@RequestMapping("emp-dept-hist")
public class EmployeeDepartmentHistoryController {
	private DepartmentDelegate dd;
	private EmployeeDepartmentHistoryDelegate edhd;
	private EmployeeServiceImp esi;
	
	@Autowired
	public EmployeeDepartmentHistoryController(DepartmentDelegate dd, EmployeeDepartmentHistoryDelegate edhd, EmployeeServiceImp esi) {
		this.dd = dd;
		this.edhd = edhd;
		this.esi = esi;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Employeedepartmenthistory> edhs = edhd.findAll();
		//System.out.println("EDHS: " + edhs.iterator().next().getEmployee());
		if(edhs.iterator().hasNext()){
			//System.out.println("EDHS: " + edhs.iterator().next().getEmployee().getBusinessentityid());
			model.addAttribute("empdepthsts", edhs);
		}
		return "emp-dept-hist/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployeedepartmenthistory(Model model, @PathVariable("id") Integer id) {
		Employeedepartmenthistory edh = edhd.findById(id);
		if (edh == null)
			throw new IllegalArgumentException("Invalid emp dept hst Id:" + id);
		
		model.addAttribute("empDeptHists", edh);
		model.addAttribute("department", dd.findAll());
		return "emp-dept-hist/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditEmployeedepartmenthistory(Model model, @ModelAttribute Employeedepartmenthistory edh) {
		edhd.update(edh);
		return "redirect:/emp-dept-hist";
	}
	
	@GetMapping("/add")
	public String addEmployeedepartmenthistory(Model model) {
		model.addAttribute("Employeedepartmenthistory", new Employeedepartmenthistory());
		model.addAttribute("departments", dd.findAll());
		model.addAttribute("employees", esi.findAll());
		return "emp-dept-hist/add";
	}

	@PostMapping("/add")
	public String addEmployeedepartmenthistoryPost(Model model, @Valid @ModelAttribute Employeedepartmenthistory edh, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {    
			System.out.println("EDH emp POST MAPPING: " + edh.getEmployee());
			model.addAttribute("Employeedepartmenthistory", new Employeedepartmenthistory());
			model.addAttribute("departments", dd.findAll());
			model.addAttribute("employees", esi.findAll());
	        return "emp-dept-hist/add";
	    } else {	
	    	
			edhd.save(edh);
	    	return "redirect:/emp-dept-hist";
	    }
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployeedepartmenthistory(Model model, @PathVariable Integer id) {
		Employeedepartmenthistory edh = edhd.findById(id);
		if (edh == null)
			throw new IllegalArgumentException("Invalid emp dept hst Id:" + id);
		edhd.delete(id);
		return "redirect:/emp-dept-hist";
	}
	
	@GetMapping("/{id}")
	public String getEmployeedepartmenthistory(Model model,@PathVariable("id") Integer id) {
		Employeedepartmenthistory dedh = edhd.findById(id);
		if (dedh == null)
			throw new IllegalArgumentException("Invalid emp dept hst Id:" + id);
		
		model.addAttribute("Employeedepartmenthistory", dedh);
		return "emp-dept-hist/information";
	}
}
