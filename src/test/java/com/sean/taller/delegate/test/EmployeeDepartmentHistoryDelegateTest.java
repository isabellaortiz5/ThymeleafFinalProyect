package com.sean.taller.delegate.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.MockRestServiceServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.taller.businessdelegate.intfcs.EmployeeDepartmentHistoryDelegate;

class EmployeeDepartmentHistoryDelegateTest {
	
	@Autowired
    private EmployeeDepartmentHistoryDelegate delegate;

    private MockRestServiceServer server;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void createServer() {
//        server = MockRestServiceServer.createServer(delegate.getRestTemplate());

    }
	
    @Test
	void addEmployeeDepartmentHistory() {
		fail("Not yet implemented");
	}
	
	@Test
	void updateEmployeeDepartmentHistory() {
		
	}
	
	@Test
	void deleteEmployeeDepartmentHistory() {
		
	}
	
	@Test
	void findByIdEmployeeDepartmentHistory() {
		
	}
	
	@Test
	void findAllEmployeeDepartmentHistory() {
		
	}

}
