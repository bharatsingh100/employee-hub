package org.example.employeehub.department;

import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    List<Department> all() {
        return departmentService.findAll();
    }

    @PostMapping("/departments")
    Department newDepartment(@RequestBody Department newDepartment) {
        return departmentService.addDepartment(newDepartment);
    }

    @GetMapping("/departments/{id}")
    Department one(@PathVariable Long id){
        return departmentService.findOne(id);
    }

    @PutMapping ("/departments/{deptId}/emp/{empId}")
    Department addEmployeeToDept(
            @PathVariable Long deptId,
            @PathVariable Long empId
    ){
        return departmentService.addEmployeeToDept(deptId,empId);
    }
}
