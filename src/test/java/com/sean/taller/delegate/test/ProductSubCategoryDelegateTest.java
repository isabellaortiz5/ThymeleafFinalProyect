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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.Application;
import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.businessdelegate.intfcs.ProductSubCategoryDelegate;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Productsubcategory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
class ProductSubCategoryDelegateTest {
	private final static String URL = "http://localhost:8080/api/product-sub-category/";
	
	@Autowired
    private ProductSubCategoryDelegate pscd;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
        server = MockRestServiceServer.createServer(pscd.getRt());

    }
    
    @Test
    public void addProductSubCategoryTest() {
        Productsubcategory psc = new Productsubcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Productsubcategory result = pscd.save(psc);

        assertNotNull(result);
        assertEquals(psc.getProductsubcategoryid(), result.getProductsubcategoryid());

        server.verify();
    }
    

	@Test
	void updateProductSubCategoryTest() {
		Productsubcategory psc = new Productsubcategory();
        try {
            server.expect(ExpectedCount.once(),
            		requestTo(URL + psc.getProductsubcategoryid()))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pscd.update(psc);

        server.verify();
		
	}
	
	@Test
	void deleteProductSubCategoryTest() {
		Productsubcategory psc = new Productsubcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        pscd.save(psc);
        server.reset();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + psc.getProductsubcategoryid()))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pscd.delete(psc.getProductsubcategoryid());

        server.verify();
		
	}
	
	@Test
	void findByIdProductSubCategoryTest() {	
		
		Productsubcategory psc = new Productsubcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + psc.getProductsubcategoryid()))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(mapper.writeValueAsString(psc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Productsubcategory response = pscd.findById(psc.getProductsubcategoryid());

        assertNotNull(response);

        assertEquals(psc.getProductsubcategoryid(), response.getProductsubcategoryid());

        server.verify();
	}
	
	@Test
	void findAllProductSubCategory() {
		
	}

}