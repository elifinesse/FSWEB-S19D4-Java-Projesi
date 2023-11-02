package com.workintech.s19d4.service;

import java.util.List;

import com.workintech.s19d4.entity.Employee;

public interface EmployeeService {
    
    List<Employee> findAll();
    Employee findById(long id);
    Employee findByEmail(String email);
    List<Employee> findByOrder();
    Employee save(Employee employee);
    List<Employee> findBySalary(double salary);
    Employee delete(long id);
}
