package com.gl.ems.service;

import com.gl.ems.dto.*;
import com.gl.ems.entity.Task;
import com.gl.ems.exception.EmployeeManagementSystemException;

import java.util.List;

public interface EmployeeManagementSystemService {

    public ResponseDTO addEmployee(EmployeeDTO employeeDTO) throws EmployeeManagementSystemException;

    public EmployeeDTO fetchEmployeeDetails(String email) throws EmployeeManagementSystemException;

    public ResponseDTO addDepartmentAndItsAssociatedProjects(DepartmentDTO departmentDTO) throws EmployeeManagementSystemException;

    public ResponseDTO deleteEmployeeAlongWithItsLoginCredential(String email) throws EmployeeManagementSystemException;

    public ResponseDTO addTasksToProject(String name,TaskDTO taskDTO) throws EmployeeManagementSystemException;

    public List<ProjectDTO> ViewAllProjectsInADepartment(String name) throws EmployeeManagementSystemException;

    public ResponseDTO updateProjectTaskStatus(String name,String title,String updatedStatus) throws EmployeeManagementSystemException;

    public List<Task> viewAllTasksOfAProject(String name) throws EmployeeManagementSystemException;

    public ResponseDTO updateEmployeePassword(String email,String password) throws EmployeeManagementSystemException;

    public ResponseDTO deleteAllTaskOfASpecificProject(String ProjName) throws EmployeeManagementSystemException;
}
