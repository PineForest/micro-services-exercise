package com.example.code.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

/**
 * TODO: Describe class.
 *
 * @author PineForest 2017-Apr-26
 */
public class Department {
    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    private List<Employee> employees;

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
