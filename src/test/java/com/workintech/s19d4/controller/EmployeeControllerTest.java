package com.workintech.s19d4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.workintech.s19d4.service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
}
