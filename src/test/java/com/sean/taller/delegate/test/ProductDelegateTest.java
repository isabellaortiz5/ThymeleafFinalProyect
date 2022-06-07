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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.Application;
import com.sean.taller.businessdelegate.intfcs.ProductDelegate;
import com.sean.taller.model.prod.Product;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
class ProductDelegateTest {
	private final static String URL = "http://localhost:8080/api/prod/";
	
	@Autowired
    private ProductDelegate pd;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
        server = MockRestServiceServer.createServer(pd.getRt());

    }
    
    @Test
    public void addProductTest() {
        Product p = new Product();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Product result = pd.save(p);

        assertNotNull(result);
        assertEquals(p.getProductid(), result.getProductid());

        server.verify();
    }
    

	@Test
	void updateProductTest() {
		Product p = new Product();
        try {
            server.expect(ExpectedCount.once(),
            		requestTo(URL + p.getProductid()))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pd.update(p);

        server.verify();
		
	}
	
	@Test
	void deleteProductTest() {
		Product p = new Product();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        pd.save(p);
        server.reset();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + p.getProductid()))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        pd.delete(p.getProductid());

        server.verify();
		
	}
	
	@Test
	void findByIdProductTest() {	
		
		Product p = new Product();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + p.getProductid()))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(mapper.writeValueAsString(p), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Product response = pd.findById(p.getProductid());

        assertNotNull(response);

        assertEquals(p.getProductid(), response.getProductid());

        server.verify();
	}
}