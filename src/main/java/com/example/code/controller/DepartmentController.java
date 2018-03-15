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
