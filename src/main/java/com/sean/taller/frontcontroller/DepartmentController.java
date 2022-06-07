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
import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;
import com.sean.taller.businessdelegate.intfcs.EmployeeDepartmentHistoryDelegate;
import com.sean.taller.model.hr.Department;

@Controller
@RequestMapping("dept")
public class DepartmentController {
	@Autowired
	private DepartmentDelegate dd;
	@Autowired
	private EmployeeDepartmentHistoryDelegate edhd;
	
	@Autowired
	public DepartmentController(DepartmentDelegate dd, EmployeeDepartmentHistoryDelegate edhd) {
		this.dd = dd;
		this.edhd = edhd;
	}
	
	@GetMapping
	public String index(Model model) {
		Iterable<Department> depts = dd.findAll();
		
		if(depts.iterator().hasNext()){
			model.addAttribute("departments", depts);
		}
		return "dept/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editDepartment(Model model, @PathVariable("id") Integer id) {
		Department d = dd.findById(id);
		if (d == null)
			throw new IllegalArgumentException("Invalid dept Id:" + id);
		
		model.addAttribute("department", d);
		model.addAttribute("empDeptHists", edhd.findAll());
		return "dept/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditDepartment(Model model, @ModelAttribute Department d) {
		dd.update(d);
		return "redirect:/dept";
	}
	
	@GetMapping("/add")
	public String addDepartment(Model model) {
		model.addAttribute("department", new Department());
		model.addAttribute("empDeptHists", edhd.findAll());
		return "dept/add";
	}

	@PostMapping("/add")
	public String addDepartmentPost(Model model, @Valid @ModelAttribute Department d, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {       
			model.addAttribute("department", new Department());
	        return "dept/add";
	    } else {	
	    	dd.save(d);
	    	return "redirect:/dept";
	    }
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDepartment(Model model, @PathVariable Integer id) {
		Department d = dd.findById(id);
		if (d == null)
			throw new IllegalArgumentException("Invalid dept Id:" + id);
		dd.delete(id);
		return "redirect:/dept";
	}
	
	@GetMapping("/{id}")
	public String getDepartment(Model model,@PathVariable("id") Integer id) {
		Department d = dd.findById(id);
		if (d == null)
			throw new IllegalArgumentException("Invalid dept Id:" + id);
		model.addAttribute("department", d);
		return "dept/information";
	}
	
}
