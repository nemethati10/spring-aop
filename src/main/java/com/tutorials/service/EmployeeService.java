package com.tutorials.service;

import com.tutorials.repository.EmployeeRepository;
import com.tutorials.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(final String name, final String id) {

        final Employee employee = new Employee();
        employee.setName(name);
        employee.setId(id);

        employeeRepository.addEmployee(employee);

        return employee;
    }

    public void throwDummyException() {
        throw new RuntimeException("Dummy Exception!");
    }

}