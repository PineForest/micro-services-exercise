package com.example.code.dao;

import com.example.code.dto.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * TODO: Describe class.
 *
 * WARNING: Not thread safe!
 *
 * @author PineForest 2017-Apr-26
 */
@Repository
public class EmployeeDao {
    @Autowired
    private SimpleObjectStore simpleObjectStore;

    public Employee get(int id) {
        Employee employee = simpleObjectStore.getEmployees().get(id);
        if (employee == null) {
            throw new RuntimeException("Unspecified error");
        }
        return employee;
    }

    public List<Employee> getAll() {
        Collection<Employee> employees = simpleObjectStore.getEmployees().values();
        return new ArrayList<>(employees);
    }

    public Employee create(Employee employee) {
        // Employee "department" is managed by DepartmentDao class
        if (employee == null || employee.getId() == null || employee.getDepartment() != null
                || simpleObjectStore.getEmployees().get(employee.getId()) != null) {
            throw new RuntimeException("Unspecified error");
        }
        simpleObjectStore.getEmployees().put(employee.getId(), employee);
        return employee;
    }

    public Employee update(Employee employee) {
        if (employee == null || employee.getId() == null || employee.getName() == null
                || simpleObjectStore.getEmployees().get(employee.getId()) == null) {
            throw new RuntimeException("Unspecified error");
        }
        int id = employee.getId();
        Employee storedEmployee = simpleObjectStore.getEmployees().get(id);
        String name = employee.getName();
        String storedName = storedEmployee.getName();
        if (name != null || storedName == null) {
            storedEmployee.setName(name);
        }
        // Employee "department" is managed by DepartmentDao class
        return storedEmployee;
    }

    public boolean delete(int id) {
        if (simpleObjectStore.getEmployees().get(id) == null) {
            throw new RuntimeException("Unspecified error");
        }
        return !(simpleObjectStore.getEmployees().remove(id) == null);
    }
}
