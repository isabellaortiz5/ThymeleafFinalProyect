package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.businessdelegate.intfcs.DepartmentDelegate;

class DepartmentDelegateTest {
	
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
