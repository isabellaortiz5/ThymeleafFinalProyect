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
import com.sean.taller.dao.intfcs.ProductCategoryDao;
import com.sean.taller.dao.intfcs.ProductSubCategoryDao;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
class ProductSubCategoryDaoTest {

	@Autowired
	private ProductSubCategoryDao pscd;
	
	@Autowired
	private ProductCategoryDao pcd;
	
	private Productsubcategory s;
	
	@BeforeEach
	void setUpSave() {
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phone");
		pcd.save(pc1);
		
		s = new Productsubcategory();
		s.setName("samsung");
		s.setRowguid(3);
		s.setProductcategory(pc1);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {
		
		pscd.save(s);

		Productsubcategory cFromDao = pscd.findById(s.getProductsubcategoryid());
		
		assertNotNull(cFromDao);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(pscd);
		
		setUpSave();
		
		pscd.save(s);
		
		s.setName("Isabella");

		pscd.update(s);
		
		assertTrue(pscd.findById(s.getProductsubcategoryid()).getName().equals("Isabella"));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {
		pscd.save(s);
		pscd.delete(s.getProductsubcategoryid());
		assertNull(pscd.findById(s.getProductsubcategoryid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(pscd);
		
		setUpSave();
		
		pscd.save(s);
	
		assertEquals(s.getName(), pscd.findById(s.getProductsubcategoryid()).getName());
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {
		
		pscd.save(s);
	
		Productsubcategory s1 = new Productsubcategory();
		s.setName("Fernando");
		s.setRowguid(6);
		
		pscd.save(s1);
		assertEquals(2, pscd.findAll().size());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByCategTest() {
		pscd.save(s);
		
		List<Productsubcategory> pscs =  pscd.findByCategory(s.getProductcategory().getProductcategoryid());
		
		pscs.forEach(psc -> {
			assertTrue(psc.getProductcategory().getProductcategoryid() == s.getProductcategory().getProductcategoryid());
		});
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByNameTest() {
		pscd.save(s);
		
		List<Productsubcategory> pscs =  pscd.findByName(s.getName());
		
		pscs.forEach(psc -> {
			assertTrue(psc.getName() == s.getName());
		});
	}
}