package com.sean.taller.service.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.sean.taller.Application;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.services.intfcs.ProductService;
import com.sean.taller.services.intfcs.ProductcategoryService;
import com.sean.taller.services.intfcs.ProductsubcategoryService;
import com.sean.taller.services.intfcs.UnitmeasureService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = Application.class)
class ProductTest {
	private ProductService ps;
	private Product p;
	
	private ProductsubcategoryService pscategS;
	private Productsubcategory pscateg;
	
	private ProductcategoryService pcategS;
	private Productcategory pcateg;
	
	private UnitmeasureService ums;
	private Unitmeasure um;
	
	@Autowired
	public ProductTest(ProductService ps, ProductsubcategoryService pscategS, ProductcategoryService pcategS, UnitmeasureService ums) {
		this.ps = ps;
		this.pscategS = pscategS;
		this.pcategS = pcategS;
		this.ums = ums;
	}
	
	@BeforeAll
	void setUp() {	
		pcateg = new Productcategory();
		pcateg.setName("categorie1");
		pcateg = pcategS.save(pcateg);
		
		pscateg = new Productsubcategory();
		pscateg.setProductcategory(pcateg);
		pscateg.setName("subcategorie1");
		pscateg = pscategS.save(pscateg);
		
		um = new Unitmeasure();
		um.setName("unitmeasure1");
		um = ums.save(um);
	}

	@Test
	@Order(1)
	public void saveProduct() {
		p = new Product();
		p.setWeight(BigDecimal.ONE);
		p.setProductsubcategory(pscateg);
		p.setSize("S");
		p.setProductnumber("1");
		p.setSellstartdate(LocalDate.of(2022, 5, 8));
		p.setSellenddate(LocalDate.of(2023, 5, 8));
		p.setUnitmeasure1(um);
		
		p = ps.save(p);
		
		Product product = ps.findById(p.getProductid());
		
		assertEquals(p.getProductsubcategory().getName(), product.getProductsubcategory().getName());
		assertEquals(p.getWeight(), product.getWeight());

	}
	
	@Test
	@Order(2)
	public void editProduct() {
		p = new Product();
		p.setWeight(BigDecimal.ONE);
		p.setProductsubcategory(pscateg);
		p.setSize("S");
		p.setProductnumber("1");
		p.setSellstartdate(LocalDate.of(2022, 5, 8));
		p.setSellenddate(LocalDate.of(2023, 5, 8));
		p.setUnitmeasure1(um);
		
		p = ps.save(p);
		
		LocalDate sd = LocalDate.of(2022, 5, 8);
		LocalDate ed = LocalDate.of(2022, 8, 2);

		p.setSellstartdate(sd);	
		p.setSellenddate(ed);
	
		ps.edit(p);
		
		Product editedp = ps.findById(p.getProductid());
		
		assertEquals(editedp.getSellstartdate(), sd);
		assertEquals(editedp.getSellenddate(), ed);

	}
}
