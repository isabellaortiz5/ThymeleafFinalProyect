package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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

import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;

class ProductSubCategoryDelegateTest {
	
	@Autowired
    private ProductSubCategoryDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
	@Test
	void addProductSubCategory() {
		Productcategory pc = new Productcategory();
		pc.setName("CategTest");
		LocalDate date = LocalDate.now();    
		pc.setModifieddate(date);
		pc.setRowguid(1);
		
		Productsubcategory psc = new Productsubcategory();
		psc.setName("SubCategTest");
		date = LocalDate.now();    
		psc.setModifieddate(date);
		psc.setRowguid(1);
		psc.setProductcategory(pc);
		
		try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
		
		Productsubcategory savedPsc = delegate.save(psc);

        assertNotNull(savedPsc);
        assertEquals(psc.getProductsubcategoryid(), savedPsc.getProductsubcategoryid());

        server.verify();
		
		fail("Not yet implemented");
	}
	
	@Test
	void updateProductSubCategory() {
		
	}
	
	@Test
	void deleteProductSubCategory() {
		
	}
	
	@Test
	void findByIdProductSubCategory() {
		
	}
	
	@Test
	void findAllProductSubCategory() {
		
	}
}
