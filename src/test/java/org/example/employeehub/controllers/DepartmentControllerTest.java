package org.example.employeehub.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employeehub.department.Department;
import org.example.employeehub.department.DepartmentService;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@WebMvcTest(org.example.employeehub.department.DepartmentController.class)
public class DepartmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

    @Test
    public void getDepartmentShouldReturnListOfDepartments() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        ArrayList<Department> mockDepartmentList = new ArrayList<Department>();
        mockDepartmentList.add(mockDepartment);
        when(departmentService.findAll()).thenReturn(mockDepartmentList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/departments")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJsonOutput = objectMapper.writeValueAsString(mockDepartmentList);

        assertEquals(expectedJsonOutput,response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void postDepartmentsShouldReturnCreatedDepartment() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        String exampleJsonDepartment = "{\"name\":\"Finance\",\"desc\":\"To manage finance\"}";
        when(departmentService.addDepartment(Mockito.any(Department.class))).thenReturn(mockDepartment);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/departments")
                .accept(MediaType.APPLICATION_JSON).content(exampleJsonDepartment).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJsonOutput = objectMapper.writeValueAsString(mockDepartment);

        assertEquals(expectedJsonOutput,response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void putEmployeeShouldReturnUpdatedDepartment() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        when(departmentService.addEmployeeToDept(Mockito.anyLong(),Mockito.anyLong())).thenReturn(mockDepartment);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/departments/1/emp/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJsonOutput = objectMapper.writeValueAsString(mockDepartment);

        assertEquals(expectedJsonOutput,response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
