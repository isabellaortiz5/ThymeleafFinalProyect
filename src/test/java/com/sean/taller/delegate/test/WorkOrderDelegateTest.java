package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.businessdelegate.intfcs.WorkOrderDelegate;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Scrapreason;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.model.prod.Workorder;

class WorkOrderDelegateTest {
	
	@Autowired
    private WorkOrderDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
    @Test
	void addWorkOrder() {
    	Productcategory pc1 = new Productcategory();
		pc1.setName("pctest");
		LocalDate date = LocalDate.now();    
		pc1.setModifieddate(date);
		pc1.setRowguid(1);
		
    	Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("psctest");
		date = LocalDate.now();    
		psc1.setModifieddate(date);
		psc1.setRowguid(1);
		psc1.setProductcategory(pc1);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("umtest");
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("umtest");
		
    	Product p1 = new Product();
		p1.setName("ptest");
		p1.setProductnumber("1");
		p1.setSellstartdate(LocalDate.of(2022, 05, 8));
		p1.setSellenddate(LocalDate.of(2023, 01, 8));
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
		p1.setSize("S");
		p1.setWeight(BigDecimal.valueOf(80));
		
		Scrapreason s1 = new Scrapreason();
		s1.setName("stest");
		
		Workorder w1 = new Workorder();
		w1.setDuedate(LocalDate.of(2022, 05, 8));
		w1.setEnddate(LocalDate.of(2022, 05, 10));
		w1.setModifieddate(LocalDate.of(2022, 05, 6));
		w1.setOrderqty(5);
		w1.setProduct(p1);
		w1.setScrappedqty(12);
		w1.setScrapreason(s1);
		w1.setStartdate(LocalDate.of(2022, 05, 1));
		
		try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(w1), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
		
		Workorder savedW = delegate.save(w1);

        assertNotNull(savedW);
        assertEquals(w1.getWorkorderid(), savedW.getWorkorderid());

        server.verify();
		
		fail("Not yet implemented");
	}
	
	@Test
	void updateWorkOrder() {
		
	}
	
	@Test
	void deleteWorkOrder() {
		
	}
	
	@Test
	void findByIdWorkOrder() {
		
	}
	
	@Test
	void findAllWorkOrder() {
		
	}

}
