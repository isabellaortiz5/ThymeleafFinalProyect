package com.sean.taller.service.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sean.taller.Application;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.repository.ProductsubcategoryRepository;
import com.sean.taller.services.intfcs.ProductService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class ProductTest {
	private Product p;
	private ProductService ps;
	
	private ProductcategoryRepository pcr;
	private ProductsubcategoryRepository pscr;
	
	private Productcategory pc;
	private Productsubcategory psc;
	
	@Autowired
	public ProductTest(Product p, ProductService ps, ProductcategoryRepository pcr, ProductsubcategoryRepository pscr) {
		this.p = p;
		this.ps = ps;
		this.pcr = pcr;
		this.pscr = pscr;
		
	}
	
	void setUp() {
		p = new Product();
		pc = new Productcategory();
		psc = new Productsubcategory();
		
		p.setProductnumber("154");
		
		p.setSellstartdate(LocalDate.of(2022, 5, 8));
		p.setSellenddate(LocalDate.of(2022, 5, 8));
		
		p.setWeight(new BigDecimal(1));
		p.setSize("");
		
		pc.setName("12345");
		psc.setName("123456");
		
		psc.setProductcategory(pc);
		
		p.setProductsubcategory(psc);
		
		pcr.save(pc);
		pscr.save(psc);
	}
	
	@Test
	void addProdTest() {
		setUp();
		assertEquals(p, ps.save(p));
	}
	
	
	@Test
	void noAddByProductNumberTest() {
		setUp();
		
		p.setProductnumber(null);
		
		assertThrows(IllegalArgumentException.class, () -> ps.save(p));
		
	}

	@Test
	void noAddBySellstartdateTest() {
		setUp();
		
		assert p.getSellstartdate().compareTo(p.getSellenddate()) >= 0;
		
		p.setSellstartdate(null);
		
		assertThrows(IllegalArgumentException.class, () -> ps.save(p));
		
	}
	
	@Test
	void noAddBySellenddateTest() {
		setUp();
		
		assert p.getSellstartdate().compareTo(p.getSellenddate()) >= 0;
		
		p.setSellenddate(null);
		
		assertThrows(IllegalArgumentException.class, () -> ps.save(p));
		
	}
	
	@Test
	void noAddByWeightTest() {
		setUp();
		
		p.setWeight(null);
		
		assertThrows(IllegalArgumentException.class, () -> ps.save(p));
		
	}
	
	@Test
	void noAddBySizeTest() {
		setUp();
		
		p.setSize(null);
		
		assertThrows(IllegalArgumentException.class, () -> ps.save(p));
		
	}

	@Test
	void noAddByProductsubcategoryTest() {
		setUp();
		
		p.setProductsubcategory(null);
		
		assertThrows(NullPointerException.class, () -> ps.save(p));
	}
	
	// ------------------------------ UPDATE 
	
	@Test
	void editProdTest() {
		setUp();
		
		Productsubcategory temp = new Productsubcategory();
		
		temp.setName("654654");
		temp.setProductcategory(pc);
		
		pscr.save(temp);
		
		p.setProductsubcategory(temp);
		
		assertThrows(Exception.class, () -> ps.edit(p));
	}
	
}
