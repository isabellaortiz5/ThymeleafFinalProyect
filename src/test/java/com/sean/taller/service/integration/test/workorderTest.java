package com.sean.taller.service.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
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
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.services.intfcs.WorkorderService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = Application.class)
class workorderTest {
	private WorkorderService wos;
	private Workorder wo;
	
	@Autowired
	public workorderTest(WorkorderService wos) {
		this.wos = wos;
	}

	@Test
	@Order(1)
	public void saveworkorder() {
		wo = new Workorder();
		wo.setOrderqty(0);
		wo.setScrappedqty(0);
		wo.setStartdate(LocalDate.of(2022, 5, 8));	
		wo.setEnddate(LocalDate.of(2022, 8, 2));	
		
		wo = wos.add(wo);
		
		Workorder workorder = wos.findById(wo.getWorkorderid());
		
		assertEquals(wo.getOrderqty(), workorder.getOrderqty());
		assertEquals(wo.getScrappedqty(), workorder.getScrappedqty());

	}
	
	@Test
	@Order(2)
	public void editworkorder() {
		int oq = 0;
		int sq = 0;
		LocalDate sd = LocalDate.of(2022, 5, 8);
		LocalDate ed = LocalDate.of(2022, 8, 2);
		LocalDate dd = LocalDate.of(2021, 5, 15);
		
		wo.setOrderqty(oq);
		wo.setScrappedqty(sq);
		wo.setStartdate(sd);	
		wo.setEnddate(ed);
		wo.setDuedate(dd);
	
		wo = wos.edit(wo);
		
		Workorder editedwo = wos.findById(wo.getWorkorderid());
		
		assertEquals(editedwo.getOrderqty(), oq);
		assertEquals(editedwo.getScrappedqty(), sq);
		assertEquals(editedwo.getStartdate(), sd);
		assertEquals(editedwo.getEnddate(), ed);
		assertEquals(editedwo.getDuedate(), dd);

	}
	
}
