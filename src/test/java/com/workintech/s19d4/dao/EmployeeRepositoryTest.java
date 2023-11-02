package com.workintech.s19d4.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workintech.s19d4.entity.Employee;

@SpringBootTest
public class EmployeeRepositoryTest {
    
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(){
        Employee employee1 = new Employee();
        employee1.setFirstName("franz");
        employee1.setLastName("kafka");
        employee1.setEmail("f@k.com");
        employee1.setSalary(20000);
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("virginia");
        employee2.setLastName("woolf");
        employee2.setEmail("v@w.com");
        employee2.setSalary(25000);
        employeeRepository.save(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("george r.r.");
        employee3.setLastName("martin");
        employee3.setEmail("g@m.com");
        employee3.setSalary(30000);
        employeeRepository.save(employee3);
    }

    @BeforeEach
    public void setUp(){
        createEmployee();
    }


    @AfterEach
    public void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    void canFindByEmail(){
        Optional<Employee> employee = employeeRepository.findByEmail("f@k.com");
        assertNotNull(employee.get());
        assertEquals("franz", employee.get().getFirstName());
    }

     @Test
    void isNullWithNonExistingEmail(){
        Optional<Employee> employee = employeeRepository.findByEmail("e@k.com");
        assertEquals(Optional.empty(), employee);
    }

    @Test
    void canOrderByLastName(){
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3, employees.size());
        Employee firstEmployee = employees.get(0);
        assertEquals("franz", firstEmployee.getFirstName());
        Employee lastEmployee = employees.get(2);
        assertEquals("woolf", lastEmployee.getLastName());
    }

    @Test
    void canFilterBySalary(){
        List<Employee> higherSalaries = employeeRepository.findBySalary(22000);
        assertEquals(2, higherSalaries.size());
    }

    @Test
    void canSortBySalary(){
        List<Employee> salaries = employeeRepository.findBySalary(19000);
        Employee employee1 = salaries.get(0);
        assertEquals("martin", employee1.getLastName());
        Employee employee3 = salaries.get(2);
        assertEquals("kafka", employee3.getLastName());
    }
}
