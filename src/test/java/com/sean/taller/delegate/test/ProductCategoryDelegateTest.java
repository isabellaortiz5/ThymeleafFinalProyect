package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.Application;
import com.sean.taller.businessdelegate.imp.ProductCategoryDelegateImp;
import com.sean.taller.businessdelegate.intfcs.ProductCategoryDelegate;
import com.sean.taller.model.prod.Productcategory;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@DirtiesContext
class ProductCategoryDelegateTest {
	private final static String URL = "http://localhost:8080/api/product-category/";
	
	@Mock
    private static RestTemplate restTemplate;

    @InjectMocks
    private static ProductCategoryDelegate bd;


    @BeforeAll
    public static void setUp() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());
    	bd = new ProductCategoryDelegateImp();
    	bd.setRt(restTemplate);
    	
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
