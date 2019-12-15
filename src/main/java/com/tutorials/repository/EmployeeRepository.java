package com.tutorials.repository;

import com.tutorials.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(final Employee employee) {
        employees.add(employee);
    }

    public int getEmployeeListSize() {
        return new ArrayList<>(employees).size();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
