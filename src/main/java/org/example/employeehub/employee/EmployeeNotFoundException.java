package org.example.employeehub.employee;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long id){
        super("could not found employee with id "+id);
    }
}
