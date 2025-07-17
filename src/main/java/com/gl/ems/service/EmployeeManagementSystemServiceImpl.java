package com.gl.ems.service;

import com.gl.ems.dto.*;
import com.gl.ems.entity.*;
import com.gl.ems.exception.EmployeeManagementSystemException;
import com.gl.ems.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeManagementSystemServiceImpl implements EmployeeManagementSystemService {

    @Autowired
    private LoginCredentialRepository loginCredentialRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseDTO addEmployee(EmployeeDTO employeeDTO) throws EmployeeManagementSystemException {
        Employee employee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (employee != null) {
            throw new EmployeeManagementSystemException("Employee Already Exists with Employee Id:" + employee.getId());
        }
        employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        LoginCredential loginCredential = new LoginCredential();
        loginCredential.setUsername(employeeDTO.getLoginCredentialsDTO().getUsername());
        loginCredential.setPassword(employeeDTO.getLoginCredentialsDTO().getPassword());

        employee.setLoginCredential(loginCredential);

        employeeRepository.save(employee);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Employee Added Successfully with Employee Id :" + employee.getId());
        return responseDTO;
    }

    @Override
    public EmployeeDTO fetchEmployeeDetails(String email) throws EmployeeManagementSystemException {

        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new EmployeeManagementSystemException("Employee Does Not Exist");
        }
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());

        LoginCredentialsDTO loginCredentialsDTO = new LoginCredentialsDTO();
        loginCredentialsDTO.setId(employee.getLoginCredential().getId());
        loginCredentialsDTO.setUsername(employee.getLoginCredential().getUsername());
        loginCredentialsDTO.setPassword(employee.getLoginCredential().getPassword());


        employeeDTO.setLoginCredentialsDTO(loginCredentialsDTO);

        return employeeDTO;
    }

    @Override
    public ResponseDTO addDepartmentAndItsAssociatedProjects(DepartmentDTO departmentDTO) throws EmployeeManagementSystemException{
        Department department=departmentRepository.findByName(departmentDTO.getName());
        if(department!=null){
            throw new EmployeeManagementSystemException("Department Already Exists");
        }
        department=new Department();
        department.setName(departmentDTO.getName());

        List<Project> projectList=new ArrayList<>();

        for (ProjectDTO projectDTO:departmentDTO.getProjectDTOS())
        {
            Project project=new Project();
            project.setName(projectDTO.getName());
            project.setLocation(projectDTO.getLocation());
            project.setDepartment(department);
            projectList.add(project);
        }

        department.setProjects(projectList);
        departmentRepository.save(department);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage("Department and its Projects are Added Successfully");
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteEmployeeAlongWithItsLoginCredential(String email) throws EmployeeManagementSystemException {
        Employee employee=employeeRepository.findByEmail(email);
        if(employee==null){
            throw  new EmployeeManagementSystemException("Employee Does Not Exists");
        }
        employeeRepository.delete(employee);

        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage("Employee Deleted Successfully with Employee ID : "+employee.getId());
        return responseDTO;
    }

    public ResponseDTO addTasksToProject(String name,TaskDTO taskDTO) throws EmployeeManagementSystemException{
        Project project=projectRepository.findByName(name);
        if(project==null){
            throw new EmployeeManagementSystemException("Project Does Not Exists");
        }
        Task task=new Task();
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setProject(project);

        taskRepository.save(task);

        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage("Task Added Successfully for the Project :"+project.getName());
        return responseDTO;
    }

    @Override
    public List<ProjectDTO> ViewAllProjectsInADepartment(String name) throws EmployeeManagementSystemException {
        Department department=departmentRepository.findByName(name);
        if(department==null){
            throw new EmployeeManagementSystemException("Department Does Not Exists");
        }
        List<Project>projectList=department.getProjects();
        List<ProjectDTO>projectDTOList=new ArrayList<>();
        for (Project project:projectList){
            ProjectDTO projectDTO=new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());
            projectDTO.setLocation(project.getLocation());
            projectDTOList.add(projectDTO);
        }

        return projectDTOList;


    }
    @Override
    public ResponseDTO updateProjectTaskStatus(String name, String title,String updatedStatus) throws EmployeeManagementSystemException {
        Project project=projectRepository.findByName(name);
        if(project==null){
            throw new EmployeeManagementSystemException("Project Does Not Exist");
        }
        Task task=taskRepository.findByTitle(title);
        if (task==null){
            throw new EmployeeManagementSystemException("Task Does Not Exist");
        }
        task.setStatus(updatedStatus);
        taskRepository.save(task);
        projectRepository.save(project);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage("Status Updated Successfully for the task :"+ task.getTitle()+" of a Project :" +project.getName());
        return responseDTO;
    }

    @Override
    public List<Task> viewAllTasksOfAProject(String name) throws EmployeeManagementSystemException {
        Project project=projectRepository.findByName(name);
        if(project==null){
            throw new EmployeeManagementSystemException("Project Does Not Exist");
        }
//        List<TaskDTO>taskDTOS=new ArrayList<>();
        List<Task> taskList;
        taskList=taskRepository.findByProject(project);
        return taskList;
    }

    @Override
    public ResponseDTO updateEmployeePassword(String email,String password) throws EmployeeManagementSystemException {
        Employee employee=employeeRepository.findByEmail(email);
        if(employee==null){
            throw new EmployeeManagementSystemException("Employee Does Not Exist");
        }
        LoginCredential loginCredential=loginCredentialRepository.findByUsername(employee.getLoginCredential().getUsername());
        loginCredential.setPassword(password);
        loginCredentialRepository.save(loginCredential);
        employeeRepository.save(employee);

       ResponseDTO responseDTO=new ResponseDTO();
       responseDTO.setMessage("Password Updated Successfully for Employee ID : "+employee.getId());
       return responseDTO;
    }

    @Override
    public ResponseDTO deleteAllTaskOfASpecificProject(String ProjName) throws EmployeeManagementSystemException {
        Project project=projectRepository.findByName(ProjName);
        if (project==null){
            throw  new EmployeeManagementSystemException("Project Does Not Exist");
        }
        List<Task>taskList=taskRepository.findByProject(project);
        taskRepository.deleteAllInBatch(taskList);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage("Tasks Deleted Successfully");
        return responseDTO;
    }
}
