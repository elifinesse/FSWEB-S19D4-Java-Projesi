package com.workintech.s19d4.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.workintech.s19d4.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    @Query("SELECT e FROM Employee e WHERE e.email=:email")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary ORDER BY e.salary DESC")
    List<Employee> findBySalary(double salary);

    @Query("SELECT e FROM Employee e ORDER BY e.lastName")
    List<Employee> findByOrder();
}
