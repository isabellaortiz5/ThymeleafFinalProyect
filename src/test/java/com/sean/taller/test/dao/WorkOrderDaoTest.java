package com.sean.taller.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sean.taller.Application;
import com.sean.taller.dao.imp.WorkOrderDaoImp;
import com.sean.taller.model.prod.Scrapreason;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.services.intfcs.ScrapreasonService;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
class WorkOrderDaoTest {
	
	@Autowired
	private WorkOrderDaoImp woDao;
	@Autowired
	private ScrapreasonService ss;
	private Workorder wo1;
	
	@BeforeEach
	public void setup() {
		Scrapreason s = new Scrapreason();
		s.setName("reason");
		ss.save(s);
		wo1 = new Workorder();
		wo1.setDuedate( LocalDate.parse("2001-03-03"));
		wo1.setOrderqty(1);
		wo1.setScrapreason(s);
	}
	
	
	@Test
	public void save() {
		woDao.save(wo1);
		
		Workorder wofromDao = woDao.findById(wo1.getWorkorderid());
		assertNotNull(wofromDao);
	}
	
	@Test
	public void edit() {
		woDao.save(wo1);
		
		Workorder woFromDao = woDao.findById(1);
		woFromDao.setDuedate( LocalDate.parse("2005-04-04"));
		woDao.update(woFromDao);
		
		assertTrue(woDao.findById(1).getDuedate().equals(LocalDate.parse("2005-04-04")));
	}
	
	@Test
	public void find() {
		Workorder wo = woDao.findById(1);
		assertTrue(wo.getDuedate().equals(LocalDate.parse("2005-04-04")));
	}
	
	@Test
	public void findAll() {
		woDao.save(wo1);
		assertTrue(woDao.findAll().size() > 0);
	}
	
	
	@Test
	public void deleteTest() {
		woDao.save(wo1);
		woDao.delete(1);
		assertNull(woDao.findById(1));
		
	}
	
	@Test
	public void findByScrapReasonTest() {
		woDao.save(wo1);
		
		List<Workorder> wos =  woDao.findByScrapReason(wo1.getScrapreason().getScrapreasonid());
		
		wos.forEach(wo -> {
			assertTrue(wo.getScrapreason().getScrapreasonid() == wo1.getScrapreason().getScrapreasonid());
		});
	}
	
	@Test
	public void findByQtyTest() {
		woDao.save(wo1);
		
		List<Workorder> wos =  woDao.findByQty(wo1.getOrderqty());
		
		wos.forEach(wo -> {
			assertTrue(wo.getOrderqty() == wo1.getOrderqty());
		});
	}
}
