package org.example.employeehub.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeRepository;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeService.class)
public class EmployeeServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeService employeeService;

    @MockBean
    private EmployeeRepository repository;

    @Test
    public void getEmployeesShouldReturnListOfEmployees() throws Exception {
        Employee mockEmployee = new Employee("Ash", "ash@gmail.com");
        ArrayList<Employee> mockEmployeeList = new ArrayList<Employee>();
        mockEmployeeList.add(mockEmployee);
        when(repository.findAll()).thenReturn(mockEmployeeList);

        assertEquals(mockEmployeeList,employeeService.findAll() , "could not find the right list of employees");

    }

    @Test
    public void postEmployeesShouldReturnCreatedEmployee() throws Exception{
        Employee mockEmployee = new Employee("Misty", "misty@gmail.com");
        when(repository.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);

        assertEquals(mockEmployee, employeeService.addEmployee(mockEmployee), "not able to create employee");

    }

    @Test
    public void shouldReturnEmployeeData() throws Exception{
        Employee mockEmployee = new Employee("Misty", "misty@gmail.com");
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockEmployee));

        assertEquals(mockEmployee, employeeService.findOne(1L), "Facing issue while fetching vehicles");
    }
}
