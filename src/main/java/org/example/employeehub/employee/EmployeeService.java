package org.example.employeehub.employee;

import org.example.employeehub.employee.Employee;
import org.example.employeehub.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    //TO ADD NEW EMPLOYEE
    public Employee addEmployee(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    //TO FETCH DATA OF EMPLOYEE USING ID
    public Employee findOne(Long id){
        return repository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));
    }

    //TO UPDATE DATA OF AN EMPLOYEE USING IT'S ID
    Employee replaceEmployee(Employee newEmployee, Long id){
        return repository.findById(id)
                .map(employee-> {
                    employee.setName(newEmployee.getName());
                    employee.setEmail(newEmployee.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
