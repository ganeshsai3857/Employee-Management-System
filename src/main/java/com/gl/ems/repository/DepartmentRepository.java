package com.gl.ems.repository;

import com.gl.ems.entity.Department;
import com.gl.ems.entity.Employee;
import com.gl.ems.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    public Department findByName(String name);


}
