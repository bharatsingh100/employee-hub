package org.example.employeehub.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    List<Employee> all() {
        return employeeService.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.addEmployee(newEmployee);
    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return employeeService.findOne(id);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee,@PathVariable Long id){
        return employeeService.replaceEmployee(newEmployee,id);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}

