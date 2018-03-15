package com.example.code.service;

import com.example.code.dao.EmployeeDao;
import com.example.code.dto.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: Describe class.
 *
 * @author PineForest 2017-Apr-26
 */
@Service
public class EmployeeService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeDao employeeDao;

    public Employee get(int id) {
        try {
            return employeeDao.get(id);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return null;
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public Employee create(Employee employee) {
        try {
            return employeeDao.create(employee);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return null;
    }

    public Employee update(Integer id, Employee employee) {
        if (id == null || employee == null || !(employee.getId() == null || employee.getId().equals(id))) {
            return null;
        }
        employee.setId(id); // previous check guaranteed property is unset or the same as "id"
        try {
            return employeeDao.update(employee);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return null;
    }

    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            return employeeDao.delete(id);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return false;
    }
}
