package com.org.EmployeeDemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column
    public String FirstName;
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    @Column
    public String LastName;
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @Column
    public  String Designation;
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    @Column
    public int Age;
    public void setAge(int Age) {
        this.Age = Age;
    }
}
