package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.Application;
import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@DirtiesContext
@Log4j2
class DepartmentDelegateTest {
	
	private final static String URL = "http://localhost:8080/api/";
	
	@Autowired
    private DepartmentDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
    @Test
	void addDepartment() {
		fail("Not yet implemented");
	}
	
	@Test
	void updateDepartment() {
		
	}
	
	@Test
	void deleteDepartment() {
		
	}
	
	@Test
	void findByIdDepartment() {
		
	}
	
	@Test
	void findAllDepartment() {
		
	}

}
