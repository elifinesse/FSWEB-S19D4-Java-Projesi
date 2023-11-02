package com.workintech.s19d4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;



@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        return employeeService.findById(id);
    }

    @GetMapping("byEmail/{email}")
    public Employee findByEmail(@PathVariable String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/byOrder")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }

    @PostMapping("/")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PostMapping("/{salary}")
    public List<Employee> findBySalary(@PathVariable double salary){
        return employeeService.findBySalary(salary);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable long id){
        return employeeService.delete(id);
    }
}
