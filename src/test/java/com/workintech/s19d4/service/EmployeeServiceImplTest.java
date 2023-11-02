package com.workintech.s19d4.service;

import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.workintech.s19d4.dao.EmployeeRepository;
import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import com.workintech.s19d4.service.EmployeeServiceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }
    
    @Test
    void findAll(){
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void canFindByOrder(){
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }

    @Test
    void canSaveEmployee(){
        Employee employee1 = new Employee();
        employee1.setFirstName("franz");
        employee1.setLastName("kafka");
        employee1.setEmail("f@k.com");
        employee1.setSalary(20000);
        employeeService.save(employee1);
        verify(employeeRepository).save(employee1);
    }

    @Test
    void canNotSaveEmployee(){
        Employee employee1 = new Employee();
        employee1.setFirstName("franz");
        employee1.setLastName("kafka");
        employee1.setEmail("f@k.com");
        employee1.setSalary(20000);

        given(employeeRepository.findByEmail("f@k.com")).willReturn(Optional.of(employee1));
        assertNull(employeeService.save(employee1));
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void canDeleteEmployee(){
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("franz");
        employee1.setLastName("kafka");
        employee1.setEmail("f@k.com");
        employee1.setSalary(20000);

        given(employeeRepository.findById(Long.valueOf(1))).willReturn(Optional.of(employee1));
        Employee removed = employeeService.delete(employee1.getId());
        verify(employeeRepository).deleteById(Long.valueOf(1));
        assertEquals("f@k.com", removed.getEmail());
    }

    @Test
    void canNotFindByEmail(){
        given(employeeRepository.findByEmail("t@t.com")).willReturn(Optional.empty());
        assertNull(employeeService.findByEmail("t@t.com"));
    }
    @Test
    void canFindByEmail(){
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("franz");
        employee1.setLastName("kafka");
        employee1.setEmail("f@k.com");
        employee1.setSalary(20000);
        given(employeeRepository.findByEmail("f@k.com")).willReturn(Optional.of(employee1));

        Employee found = employeeService.findByEmail("f@k.com");
        assertNotNull(found);
        assertEquals("franz", found.getFirstName());
    }

    @Test
    void canFindBySalary(){
        Employee employee1 = new Employee();
        employee1.setEmail("f@k.com");
        given(employeeRepository.findBySalary(15000)).willReturn(List.of(employee1));
        List<Employee> result = employeeService.findBySalary(15000);
        verify(employeeRepository).findBySalary(15000);
        assertEquals(result.get(0).getEmail(), "f@k.com");
    }

    @Test
    void canNotFindBySalary(){
        given(employeeRepository.findBySalary(15000)).willReturn(List.of());
        List<Employee> result = employeeService.findBySalary(15000);
        verify(employeeRepository).findBySalary(15000);
        assertEquals(result, List.of());
    }

    @Test
    void canSortBySalary(){
        Employee employee1 = new Employee();
        employee1.setSalary(20000);
        Employee employee2 = new Employee();
        employee2.setSalary(25000);
        Employee employee3 = new Employee();
        employee3.setSalary(30000);

        given(employeeRepository.findBySalary(15000)).willReturn(List.of(employee1, employee2, employee3));

        List<Employee> result = employeeService.findBySalary(15000);

        System.out.println(result);

        verify(employeeRepository).findBySalary(15000);
        

    } 
}
