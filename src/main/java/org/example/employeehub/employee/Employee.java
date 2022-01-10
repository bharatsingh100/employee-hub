package org.example.employeehub.employee;

import org.example.employeehub.department.Department;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String email;

    public Employee() {}

    public Employee(String name,String email){
        this.name = name;
        this.email = email;
    }

    @ManyToMany(mappedBy = "includedEmployees",fetch = FetchType.LAZY)
    private Set<Department> departments = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
