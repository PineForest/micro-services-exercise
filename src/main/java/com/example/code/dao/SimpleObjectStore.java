package com.example.code.dao;

import com.example.code.dto.Department;
import com.example.code.dto.Employee;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Describe class.
 *
 * @author PineForest 2017-Apr-26
 */
@Component
public class SimpleObjectStore {
    private final static Map<Integer, Department> DEPARTMENTS = new HashMap<>();
    private final static Map<Integer, Employee> EMPLOYEES = new HashMap<>();

    public Map<Integer, Department> getDepartments() {
        return SimpleObjectStore.DEPARTMENTS;
    }

    public Map<Integer, Employee> getEmployees() {
        return SimpleObjectStore.EMPLOYEES;
    }
}
