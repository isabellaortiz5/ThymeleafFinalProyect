package com.sean.taller.frontcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;
import com.sean.taller.businessdelegate.intfcs.EmployeeDepartmentHistoryDelegate;
import com.sean.taller.model.hr.Department;
import com.sean.taller.model.hr.Employeedepartmenthistory;

@Controller
@RequestMapping("emp-dept-hist")
public class EmployeeDepartmentHistoryController {
	@Autowired
	private DepartmentDelegate dd;
	@Autowired
	private EmployeeDepartmentHistoryDelegate edhd;
	
	@Autowired
	public EmployeeDepartmentHistoryController(DepartmentDelegate dd, EmployeeDepartmentHistoryDelegate edhd) {
		this.dd = dd;
		this.edhd = edhd;
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
}
