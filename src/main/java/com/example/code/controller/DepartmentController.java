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
package com.example.code.controller;

import com.example.code.dto.Department;
import com.example.code.dto.Employee;
import com.example.code.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TODO: Describe class.
 *
 * @author PineForest 2017-Apr-26
 */
@RestController
public class DepartmentController {
    private final static Employee EMPTY_EMPLOYEE = new Employee();

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getDepartments(HttpServletResponse response) {
        List<Department> departments = departmentService.getAll();
        if (departments == null || departments.isEmpty()) {
            departments = null;
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.OK.value());
        }
        return departments;
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (id == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Department resultDepartment = departmentService.get(id);
        response.setStatus(resultDepartment == null ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        return resultDepartment;
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department, HttpServletResponse response) {
        if (department == null || department.getId() == null || department.getName() == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Department resultDepartment = departmentService.create(department);
        response.setStatus(resultDepartment == null ? HttpStatus.NOT_MODIFIED.value() : HttpStatus.CREATED.value());
        return resultDepartment;
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Integer id, @RequestBody Department department,
            HttpServletResponse response) {
        if (id == null || department == null || department.getId() != null || department.getName() == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Department resultDepartment = departmentService.update(id, department);
        response.setStatus(resultDepartment == null ? HttpStatus.NOT_MODIFIED.value() : HttpStatus.OK.value());
        return resultDepartment;
    }

    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (id == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (departmentService.delete(id)){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
        }
    }
}
