package com.sean.taller.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.Application;
import com.sean.taller.dao.imp.ProductDaoImp;
import com.sean.taller.dao.intfcs.ProductCategoryDao;
import com.sean.taller.dao.intfcs.ProductSubCategoryDao;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.services.intfcs.UnitmeasureService;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
class ProductDaoTest {
	@Autowired
	private ProductDaoImp pd;
	@Autowired
	private ProductSubCategoryDao pscDao;
	@Autowired
	private ProductCategoryDao pcDao;
	@Autowired
	private UnitmeasureService ums;
	
	private Product p1;
	
	@BeforeEach
	public void setup() {
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phones");
		pcDao.save(pc1);
		
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("IOS");
		psc1.setProductcategory(pc1);
		pscDao.save(psc1);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg2");
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("ml2");
		ums.save(um1);
		ums.save(um2);
		
		p1 = new Product();
		p1.setName("iphone");
		p1.setDaystomanufacture(1250);
		p1.setProductnumber("123");
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save() {
		pd.save(p1);
		Product pfromDao = pd.findById(p1.getProductid());
		assertNotNull(pfromDao);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void edit() {
		pd.save(p1);
		
		p1.setName("Isabella");

		pd.update(p1);
		
		assertTrue(pd.findById(p1.getProductid()).getName().equals("Isabella"));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void find() {
		pd.save(p1);
		assertEquals(p1.getName(), pd.findById(p1.getProductid()).getName());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAll() {
		pd.save(p1);
		assertTrue(pd.findAll().size() > 0);
	}
	
	
	@Test
	public void findByScrapReasonTest() {
		pd.save(p1);
		
		List<Product> ps =  pd.findBySubCategory(p1.getProductsubcategory().getProductsubcategoryid());
		
		ps.forEach(p -> {
			assertTrue(p.getProductsubcategory().getProductsubcategoryid() == p1.getProductsubcategory().getProductsubcategoryid());
		});
	}
	
	@Test
	public void findByUnitMeasureTest() {
		pd.save(p1);
		
		List<Product> ps =  pd.findByUnitMeasure(p1.getUnitmeasure1().getUnitmeasurecode());
		
		ps.forEach(p -> {
			assertTrue(p.getUnitmeasure1().getUnitmeasurecode() == p1.getUnitmeasure1().getUnitmeasurecode());
		});
	}
}
