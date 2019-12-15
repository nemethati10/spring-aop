package com.tutorials.controller;

import com.tutorials.model.Employee;
import com.tutorials.repository.EmployeeRepository;
import com.tutorials.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/create/", method = RequestMethod.GET)
    public Employee addEmployee(@RequestParam("name") final String name, @RequestParam("id") final String id) {

        return employeeService.createEmployee(name, id);
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public Integer getEmployeesNumber() {

        return employeeRepository.getEmployeeListSize();
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @GetMapping(value = "/exception")
    public void getException() {
        try {
            employeeService.throwDummyException();
        } catch (final RuntimeException exception) {

        }

    }


}