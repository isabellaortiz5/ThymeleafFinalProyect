package com.sean.taller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.sean.taller.model.hr.Department;
import com.sean.taller.model.hr.Employee;
import com.sean.taller.model.hr.Employeedepartmenthistory;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Scrapreason;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.services.imp.UserServiceImp;
import com.sean.taller.services.intfcs.DepartmentService;
import com.sean.taller.services.intfcs.EmployeeDepartmentHistoryService;
import com.sean.taller.services.intfcs.EmployeeService;
import com.sean.taller.services.intfcs.ProductService;
import com.sean.taller.services.intfcs.ProductcategoryService;
import com.sean.taller.services.intfcs.ProductsubcategoryService;
import com.sean.taller.services.intfcs.ScrapreasonService;
import com.sean.taller.services.intfcs.UnitmeasureService;
import com.sean.taller.services.intfcs.UserService;
import com.sean.taller.services.intfcs.WorkorderService;
import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sean.taller"})	
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Application.class, args);
		
		UserService usi = c.getBean(UserServiceImp.class);
		
		UserEntity ue1 = new UserEntity();
		ue1.setId(123456);
		ue1.setUsername("admin");
		ue1.setPassword("{noop}admin");
		ue1.setType(UserType.ADMINISTRATOR);
		usi.save(ue1);
		
		UserEntity ue2 = new UserEntity();
		ue2.setId(456789);
		ue2.setUsername("operator");
		ue2.setPassword("{noop}operator");
		ue2.setType(UserType.OPERATOR);
		usi.save(ue2);
		
		ProductcategoryService pcs = c.getBean(ProductcategoryService.class);
		
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phone");
		LocalDate date = LocalDate.now();    
		pc1.setModifieddate(date);
		pc1.setRowguid(1);
		Productcategory savedCateg = pcs.save(pc1);
		
		ProductsubcategoryService pscs = c.getBean(ProductsubcategoryService.class);
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("Android");
		date = LocalDate.now();    
		psc1.setModifieddate(date);
		psc1.setRowguid(1);
		psc1.setProductcategory(pcs.findById(savedCateg.getProductcategoryid()));
		pscs.save(psc1);
		
		UnitmeasureService ums =  c.getBean(UnitmeasureService.class);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg");
		ums.save(um1);
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("cm");
		ums.save(um2);

		ProductService ps =  c.getBean(ProductService.class);
		Product p1 = new Product();
		p1.setName("samsung");
		p1.setProductnumber("1");
		p1.setSellstartdate(LocalDate.of(2022, 05, 8));
		p1.setSellenddate(LocalDate.of(2023, 01, 8));
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
		p1.setSize("S");
		p1.setWeight(BigDecimal.valueOf(80));
		ps.save(p1);
		
		ScrapreasonService s = c.getBean(ScrapreasonService.class);
		Scrapreason s1 = new Scrapreason();
		s1.setName("damaged");
		s.save(s1);
		
		WorkorderService wo = c.getBean(WorkorderService.class);
		Workorder w1 = new Workorder();
		w1.setDuedate(LocalDate.of(2022, 05, 8));
		w1.setEnddate(LocalDate.of(2022, 05, 10));
		w1.setModifieddate(LocalDate.of(2022, 05, 6));
		w1.setOrderqty(5);
		w1.setProduct(p1);
		w1.setScrappedqty(12);
		w1.setScrapreason(s1);
		w1.setStartdate(LocalDate.of(2022, 05, 1));
		wo.add(w1);
		
		// ENTIDADES TALLER FINAL 
		
		DepartmentService ds = c.getBean(DepartmentService.class);
		Department d = new Department();
		d.setGroupname("group1");
		d.setName("Human resources");
		d.setModifieddate(LocalDate.now());
		ds.save(d);
		
		EmployeeService es =  c.getBean(EmployeeService.class);
		
		Employee e1 = new Employee();
		e1.setJobtitle("Doctor");
		e1.setBusinessentityid(1);
		es.save(e1);
		Employee e2 = new Employee();
		e2.setJobtitle("Pedriatitian");
		e2.setBusinessentityid(2);
		es.save(e2);
		
		EmployeeDepartmentHistoryService emdhs = c.getBean(EmployeeDepartmentHistoryService.class);
		
		Employeedepartmenthistory edh = new Employeedepartmenthistory();
		edh.setEmployee(e2);
		edh.setDepartment(d);
		edh.setModifieddate(LocalDate.now());
		edh.setEnddate(LocalDate.of(2022, 05, 6));
		emdhs.save(edh);
	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}


