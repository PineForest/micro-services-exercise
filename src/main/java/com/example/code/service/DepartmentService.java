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
