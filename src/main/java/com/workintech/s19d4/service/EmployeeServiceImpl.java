package com.workintech.s19d4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workintech.s19d4.dao.EmployeeRepository;
import com.workintech.s19d4.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if(employeeOpt.isPresent()){
            return employeeOpt.get();
        } 
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
        if(employeeOpt.isPresent()){
            return employeeOpt.get();
        }
        return null;
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }

    @Override
    public Employee save(Employee employee) {
        Employee found = findByEmail(employee.getEmail());
        if(found != null){
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public Employee delete(long id) {
        Employee found = findById(id);
        employeeRepository.deleteById(id);
        return found;
    }
    
}
