package com.gl.ems.repository;

import com.gl.ems.entity.Department;
import com.gl.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    public Employee findByEmail(String email);

}
