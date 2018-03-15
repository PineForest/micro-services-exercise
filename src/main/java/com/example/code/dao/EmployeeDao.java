/*
MIT License

Copyright (c) 2018 David Williams

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
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
