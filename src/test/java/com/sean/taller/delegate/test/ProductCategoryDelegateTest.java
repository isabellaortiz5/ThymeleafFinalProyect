package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
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
import com.sean.taller.businessdelegate.intfcs.ProductCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
class ProductCategoryDelegateTest {
	private final static String URL = "http://localhost:8080/api/product-category/";
	
	@Autowired
    private ProductCategoryDelegate pcd;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
        server = MockRestServiceServer.createServer(pcd.getRt());

    }
    
    @Test
    public void addProductCategoryTest() {
        Productcategory pc = new Productcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Productcategory result = pcd.save(pc);

        assertNotNull(result);
        assertEquals(pc.getProductcategoryid(), result.getProductcategoryid());

        server.verify();
    }
    

	@Test
	void updateProductCategoryTest() {
		Productcategory pc = new Productcategory();
        try {
            server.expect(ExpectedCount.once(),
            		requestTo(URL + pc.getProductcategoryid()))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pcd.update(pc);

        server.verify();
		
	}
	
	@Test
	void deleteProductCategoryTest() {
		Productcategory pc = new Productcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        pcd.save(pc);
        server.reset();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + pc.getProductcategoryid()))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pcd.delete(pc.getProductcategoryid());

        server.verify();
		
	}
	
	@Test
	void findByIdProductCategoryTest() {	
		
		Productcategory pc = new Productcategory();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + pc.getProductcategoryid()))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(mapper.writeValueAsString(pc), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Productcategory response = pcd.findById(pc.getProductcategoryid());

        assertNotNull(response);

        assertEquals(pc.getProductcategoryid(), response.getProductcategoryid());

        server.verify();
	}
	

}
