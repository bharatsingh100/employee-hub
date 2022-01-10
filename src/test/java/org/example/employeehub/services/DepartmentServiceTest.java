package org.example.employeehub.services;

import org.example.employeehub.department.Department;
import org.example.employeehub.department.DepartmentRepository;
import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeRepository;
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

@WebMvcTest(org.example.employeehub.department.DepartmentService.class)
public class DepartmentServiceTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentRepository repository;

    @Autowired
    org.example.employeehub.department.DepartmentService departmentService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void getDepartmentShouldReturnListOfDepartments() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        ArrayList<Department> mockDepartmentList = new ArrayList<Department>();
        mockDepartmentList.add(mockDepartment);
        Mockito.when(repository.findAll()).thenReturn(mockDepartmentList);

        assertEquals(mockDepartmentList,departmentService.findAll());

    }

    @Test
    public void postDepartmentsShouldReturnCreatedDepartment() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        String exampleJsonDepartment = "{\"name\":\"Finance\",\"desc\":\"To manage finance\"}";
        when(repository.save(Mockito.any(Department.class))).thenReturn(mockDepartment);

        assertEquals(mockDepartment,departmentService.addDepartment(mockDepartment));

    }

    @Test
    public void putEmployeeShouldReturnUpdatedDepartment() throws Exception{
        Department mockDepartment = new Department("Finance","To manage finance");
        Employee mockEmployee = new Employee("Ash","ash@gmail.com");
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockDepartment));
        when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockEmployee));
        mockDepartment.addEmployee(mockEmployee);
        when(repository.save(Mockito.any(Department.class))).thenReturn(mockDepartment);

        assertEquals(mockDepartment,departmentService.addEmployeeToDept(1L, 1L));

    }
}
