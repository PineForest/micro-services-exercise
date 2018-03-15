package com.example.code.dao;

import com.example.code.dto.Department;
import com.example.code.dto.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Describe class.
 *
 * WARNING: Not thread safe!
 *
 * @author PineForest 2017-Apr-26
 */
@Repository
public class DepartmentDao {
    @Autowired
    private SimpleObjectStore simpleObjectStore;

    public Department get(int id) {
        Department department = simpleObjectStore.getDepartments().get(id);
        if (department == null) {
            throw new RuntimeException("Unspecified error");
        }
        return department;
    }

    public List<Department> getAll() {
        Collection<Department> department = simpleObjectStore.getDepartments().values();
        return new ArrayList<>(department);
    }

    public Department create(Department department) {
        // Employee "department" is managed by DepartmentDao class
        if (department == null || department.getId() == null
                || simpleObjectStore.getDepartments().get(department.getId()) != null) {
            throw new RuntimeException("Unspecified error");
        }
        if (!updateEmployeeReferences(department)) {
            return null;
        }
        simpleObjectStore.getDepartments().put(department.getId(), department);
        return department;
    }

    public Department update(Department department) {
        if (department == null || department.getId() == null || department.getName() == null
                || simpleObjectStore.getDepartments().get(department.getId()) == null) {
            throw new RuntimeException("Unspecified error");
        }
        Department storedDepartment = simpleObjectStore.getDepartments().get(department.getId());
        String name = department.getName();
        String storedName = storedDepartment.getName();
        if (name != null || storedName == null) {
            storedDepartment.setName(name);
        }
        if (!updateEmployeeReferences(department)) {
            return null;
        }
        storedDepartment.setEmployees(department.getEmployees());
        return storedDepartment;
    }

    public boolean delete(int id) {
        if (simpleObjectStore.getDepartments().get(id) == null) {
            throw new RuntimeException("Unspecified error");
        }
        return !(simpleObjectStore.getDepartments().remove(id) == null);
    }

    private boolean updateEmployeeReferences(final Department department) {
        List<Employee> departmentEmployees = department.getEmployees();
        if (departmentEmployees != null) {
            final Map<Integer, Employee> employees = simpleObjectStore.getEmployees();
            if (departmentEmployees.stream().anyMatch(e -> employees.get(e.getId()) == null)) {
                return false;
            }
            // Replace employees passed in with references to stored instances
            department.setEmployees(departmentEmployees.stream().map(e -> employees.get(e.getId()))
                    .collect(Collectors.toList()));
            // Now correct employee back-reference to the department
            department.getEmployees().forEach(e -> e.setDepartment(department));
        }
        return true;
    }
}
