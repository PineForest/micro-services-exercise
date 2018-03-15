package com.example.code.controller;

import com.example.code.dto.Employee;
import com.example.code.service.EmployeeService;

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
public class EmployeeController {
    private final static Employee EMPTY_EMPLOYEE = new Employee();

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(HttpServletResponse response) {
        List<Employee> employees = employeeService.getAll();
        if (employees == null || employees.isEmpty()) {
            employees = null;
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.OK.value());
        }
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (id == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Employee resultEmployee = employeeService.get(id);
        response.setStatus(resultEmployee == null ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        return resultEmployee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        if (employee == null || employee.getId() == null || employee.getName() == null
                || employee.getDepartment() != null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Employee resultEmployee = employeeService.create(employee);
        response.setStatus(resultEmployee == null ? HttpStatus.NOT_MODIFIED.value() : HttpStatus.CREATED.value());
        return resultEmployee;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee,
            HttpServletResponse response) {
        if (id == null || employee == null || employee.getId() != null || employee.getName() == null
                || employee.getDepartment() != null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        Employee resultEmployee = employeeService.update(id, employee);
        response.setStatus(resultEmployee == null ? HttpStatus.NOT_MODIFIED.value() : HttpStatus.OK.value());
        return resultEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id, HttpServletResponse response) {
        if (id == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (employeeService.delete(id)){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
        }
    }
}
