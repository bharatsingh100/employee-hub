package org.example.employeehub.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(org.example.employeehub.employee.EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployeesShouldReturnListOfEmployees() throws Exception {
        Employee mockEmployee = new Employee("Ash", "ash@gmail.com");
        ArrayList<Employee> mockEmployeeList = new ArrayList<Employee>();
        mockEmployeeList.add(mockEmployee);

        when(employeeService.findAll()).thenReturn(mockEmployeeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedOutputInJson = objectMapper.writeValueAsString(mockEmployeeList);

        assertEquals(expectedOutputInJson, response.getContentAsString(), "Facing issue while fetching vehicles");
        assertEquals(HttpStatus.OK.value(), response.getStatus(), "Invalid response code other than 200 OK");

    }

    @Test
    public void postEmployeesShouldReturnCreatedEmployee() throws Exception{
        Employee mockEmployee = new Employee("Misty", "misty@gmail.com");
        when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        String exampleEmployeeJson = "{\"name\":\"Misty\",\"email\":\"misty@gmail.com\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON).content(exampleEmployeeJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedOutputInJson = objectMapper.writeValueAsString(mockEmployee);


        assertEquals(expectedOutputInJson, response.getContentAsString(), "Facing issue while fetching vehicles");
        assertEquals(HttpStatus.OK.value(), response.getStatus(), "Invalid response code other than 200 OK");
    }

    @Test
    public void shouldReturnEmployeeData() throws Exception{
        Employee mockEmployee = new Employee("Misty", "misty@gmail.com");
        when(employeeService.findOne(Mockito.anyLong())).thenReturn(mockEmployee);

        String mockId = "1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/"+mockId)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedOutputInJson = objectMapper.writeValueAsString(mockEmployee);

        assertEquals(expectedOutputInJson, response.getContentAsString(), "Facing issue while fetching vehicles");
        assertEquals(HttpStatus.OK.value(), response.getStatus(), "Invalid response code other than 200 OK");
    }
}