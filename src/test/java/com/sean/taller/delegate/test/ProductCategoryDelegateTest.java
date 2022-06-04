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
import com.sean.taller.businessdelegate.intfcs.ProductCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;

class ProductCategoryDelegateTest {
	
	@Autowired
    private ProductCategoryDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
    @Test
	void addProductCategory() {
		Productcategory pc = new Productcategory();
		pc.setName("CategTest");
		LocalDate date = LocalDate.now();    
		pc.setModifieddate(date);
		pc.setRowguid(1);
		
		try {
            server.expect(ExpectedCount.once(),
                    requestTo(new URI("http://localhost:8080")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        } catch (URISyntaxException | JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
		
		Productcategory savedPc = delegate.save(pc);

        assertNotNull(savedPc);
        assertEquals(pc.getProductcategoryid(), savedPc.getProductcategoryid());

        server.verify();
		
		fail("Not yet implemented");
	}
	
	@Test
	void updateProductCategory() {
		
	}
	
	@Test
	void deleteProductCategory() {
		
	}
	
	@Test
	void findByIdProductSubCategory() {
		
	}
	
	@Test
	void findAllProductCategory() {
		
	}

}
