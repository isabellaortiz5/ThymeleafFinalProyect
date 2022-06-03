package com.sean.taller.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.Application;
import com.sean.taller.dao.imp.ProductCategoryDaoImp;
import com.sean.taller.model.prod.Productcategory;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
class ProductCategoryDaoTest {


	@Autowired
	private ProductCategoryDaoImp pcd;
	
	private Productcategory c;
	
	@BeforeEach
	void setUpSave() {
		c = new Productcategory();
		c.setName("Esteban");
		c.setRowguid(3);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {
		pcd.save(c);
		Productcategory saved = pcd.findById(c.getProductcategoryid());
		assertNotNull(saved);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {
		pcd.save(c);
		c.setName("Isabella");
		pcd.update(c);
		
		assertTrue(pcd.findById(c.getProductcategoryid()).getName().equals("Isabella"));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {
		pcd.save(c);
		pcd.delete(c.getProductcategoryid());
		
		assertNull(pcd.findById(c.getProductcategoryid()));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {
		pcd.save(c);
		
		assertEquals(c.getName(), pcd.findById(c.getProductcategoryid()).getName());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {
		pcd.save(c);
		
		assertTrue(pcd.findAll().size() > 0);
	}

}