package org.example.employeehub.department;

import org.example.employeehub.employee.Employee;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    private @Id @GeneratedValue Long id;
    private String name;
    private String desc;

    Department() {}

    public Department(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name="employee_included",
        joinColumns={
            @JoinColumn(name="department_id", referencedColumnName="id",
                nullable = false,updatable = false)
        },
        inverseJoinColumns ={
            @JoinColumn(name="employment_id",referencedColumnName = "id",
                nullable = false,updatable = false)
        }
    )
    Set<Employee> includedEmployees = new HashSet<>();

    public void addEmployee(Employee emp){
        this.includedEmployees.add(emp);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
