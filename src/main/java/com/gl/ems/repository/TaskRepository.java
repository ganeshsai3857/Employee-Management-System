package com.gl.ems.repository;

import com.gl.ems.entity.Project;
import com.gl.ems.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    public Task findByTitle(String title);
    public List<Task> findByProject(Project project);
}
