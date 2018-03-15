package com.example.code.service;

import com.example.code.dao.DepartmentDao;
import com.example.code.dto.Department;
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
public class DepartmentService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentDao departmentDao;

    public Department get(int id) {
        try {
            return departmentDao.get(id);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return null;
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public Department create(Department department) {
        try {
            return departmentDao.create(department);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return null;
    }

    public Department update(Integer id, Department department) {
        if (id == null || department == null || !(department.getId() == null || department.getId().equals(id))) {
            return null;
        }
        department.setId(id); // previous check guaranteed property is unset or the same as "id"
        try {
            return departmentDao.update(department);
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
            return departmentDao.delete(id);
        } catch (Throwable t) {
            LOGGER.warn(t.getMessage(), t);
        }
        return false;
    }
}
