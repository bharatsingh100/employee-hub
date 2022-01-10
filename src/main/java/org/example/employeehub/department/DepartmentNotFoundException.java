package org.example.employeehub.department;

public class DepartmentNotFoundException extends RuntimeException{
    DepartmentNotFoundException(Long id){
        super("could not find department with id "+id);
    }
}
