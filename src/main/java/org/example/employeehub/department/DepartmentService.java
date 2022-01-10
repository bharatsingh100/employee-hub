package org.example.employeehub.department;

import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentService {
    @Autowired
    DepartmentRepository repository;

    @Autowired
    EmployeeRepository empRepository;

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department addDepartment(Department newDepartment){
        return repository.save(newDepartment);
    }

    public Department findOne(Long id){
        return repository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
    }

    public Department addEmployeeToDept(Long deptId,Long empId){
        Department dept = repository.findById(deptId).get();
        Employee emp = empRepository.findById(empId).get();
        dept.includedEmployees.add(emp);
        return repository.save(dept);
    }
}
