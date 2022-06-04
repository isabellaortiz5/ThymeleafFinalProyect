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
import com.sean.taller.businessdelegate.intfcs.ProductDelegate;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;

class ProductDelegateTest {
	
	@Autowired
    private ProductDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
    @Test
	void addProduct() {
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
		
		try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(p1), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
		
		Product savedP = delegate.save(p1);

        assertNotNull(savedP);
        assertEquals(p1.getProductid(), savedP.getProductid());

        server.verify();
		
		fail("Not yet implemented");
	}
	
	@Test
	void updateProduct() {
		
	}
	
	@Test
	void deleteProduct() {
		
	}
	
	@Test
	void findByIdProduct() {
		
	}
	
	@Test
	void findAllProduct() {
		
	}

}
