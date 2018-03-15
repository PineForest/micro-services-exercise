package com.example.code.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * TODO: Describe class.
 *
 * @author PineForest 2017-Apr-26
 */
public class Employee {
    private Integer id = null;
    private String name = null;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonBackReference
    private Department department = null;

    public Employee() {
    }

    public Employee(Integer id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
