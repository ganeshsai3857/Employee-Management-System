package com.gl.ems.repository;

import com.gl.ems.entity.Department;
import com.gl.ems.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    public Project findByName(String name);

}
