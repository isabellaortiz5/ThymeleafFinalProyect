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
import com.sean.taller.businessdelegate.intfcs.WorkOrderDelegate;
import com.sean.taller.model.prod.Workorder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
class WorkOrderDelegateTest {
	private final static String URL = "http://localhost:8080/api/work-ord/";
	
	@Autowired
    private WorkOrderDelegate wod;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
        server = MockRestServiceServer.createServer(wod.getRt());

    }
    
    @Test
    public void addProductCategoryTest() {
        Workorder wo = new Workorder();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(wo), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Workorder result = wod.save(wo);

        assertNotNull(result);
        assertEquals(wo.getWorkorderid(), result.getWorkorderid());

        server.verify();
    }
    

	@Test
	void updateProductCategoryTest() {
		Workorder wo = new Workorder();
        try {
            server.expect(ExpectedCount.once(),
            		requestTo(URL + wo.getWorkorderid()))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withSuccess(mapper.writeValueAsString(wo), MediaType.APPLICATION_JSON));
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        wod.update(wo);

        server.verify();
		
	}
	
	@Test
	void deleteProductCategoryTest() {
		Workorder wo = new Workorder();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withSuccess(mapper.writeValueAsString(wo), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        wod.save(wo);
        server.reset();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + wo.getWorkorderid()))
                    .andExpect(method(HttpMethod.DELETE))
                    .andRespond(withSuccess(mapper.writeValueAsString(wo), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        wod.delete(wo.getWorkorderid());

        server.verify();
		
	}
	
	@Test
	void findByIdProductCategoryTest() {	
		
		Workorder wo = new Workorder();

        try {
            server.expect(ExpectedCount.once(),
                    requestTo(URL + wo.getWorkorderid()))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(mapper.writeValueAsString(wo), MediaType.APPLICATION_JSON));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }

        Workorder response = wod.findById(wo.getWorkorderid());

        assertNotNull(response);

        assertEquals(wo.getWorkorderid(), response.getWorkorderid());

        server.verify();
	}
	
	@Test
	void findAllProductCategory() {
		
	}

}
