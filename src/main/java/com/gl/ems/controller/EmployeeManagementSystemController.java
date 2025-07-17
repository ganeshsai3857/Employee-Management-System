package com.gl.ems.controller;

import com.gl.ems.dto.*;
        import com.gl.ems.entity.Task;
import com.gl.ems.exception.EmployeeManagementSystemException;
import com.gl.ems.service.EmployeeManagementSystemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
        import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/ems")
@CrossOrigin
@Validated
public class EmployeeManagementSystemController {

    @Autowired
    private EmployeeManagementSystemService employeeManagementSystemService;

    @PostMapping("/add-employee")
    public ResponseEntity<ResponseDTO>addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.addEmployee(employeeDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/fetch-employee/{email}")
    public ResponseEntity<EmployeeDTO>fetchEmployeeDetails(@PathVariable @Email(message = "Email Should be in Valid Format!!!") String email) throws EmployeeManagementSystemException{
        EmployeeDTO employeeDTO=employeeManagementSystemService.fetchEmployeeDetails(email);
        return new ResponseEntity<>(employeeDTO,HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/delete-employee/{email}")
    public ResponseEntity<ResponseDTO> deleteEmployeeAlongWithItsLoginCredential(@PathVariable @Email(message = "Email Should be in Valid Format!!!") String email) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.deleteEmployeeAlongWithItsLoginCredential(email);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }


    @PostMapping("/add-department-projects")
    public ResponseEntity<ResponseDTO> addDepartmentAndItsAssociatedProjects(@RequestBody  @Valid DepartmentDTO departmentDTO) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.addDepartmentAndItsAssociatedProjects(departmentDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-task-projects/{name}")
    public ResponseEntity<ResponseDTO> addTasksToProject(@PathVariable @NotBlank(message = "Project Name Should not be Null or Blank") String name, @RequestBody @Valid TaskDTO taskDTO) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.addTasksToProject(name,taskDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/view-projects-department/{name}")
    public ResponseEntity<List<ProjectDTO>> ViewAllProjectsInADepartment(@PathVariable @NotBlank(message = "Department Name Should not be Null or Blank") String name) throws EmployeeManagementSystemException{
        List<ProjectDTO>projectDTOList=employeeManagementSystemService.ViewAllProjectsInADepartment(name);
        return  new ResponseEntity<>(projectDTOList,HttpStatus.FOUND);
    }

    @PutMapping("/update-project-task-status/{name}/{title}/{updatedStatus}")
    public ResponseEntity<ResponseDTO>updateProjectTaskStatus(@PathVariable @NotBlank(message = "Project Name Should not be Null or Blank") String name,@PathVariable @NotBlank(message = "Task Title Should not be Null or Blank")String title,@PathVariable @NotNull(message = "Status Should not be Null")String updatedStatus) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.updateProjectTaskStatus(name, title, updatedStatus);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/view-tasks-project/{name}")
    public ResponseEntity<List<Task>>viewAllTasksOfAProject(@PathVariable @NotBlank(message = "Name Should not be Null or Blank") String name) throws EmployeeManagementSystemException{
        List<Task>taskList=employeeManagementSystemService.viewAllTasksOfAProject(name);
        return new ResponseEntity<>(taskList,HttpStatus.FOUND);
    }

    @PutMapping("/update-employee-password/{email}/{password}")
    public ResponseEntity<ResponseDTO>updateEmployeePassword(@PathVariable @Email(message = "Email Should be in Valid Format!!!") String email,@PathVariable  @NotBlank(message = "Password Should not be Null or Blank")
    @Size(min = 8,message = "password must be at-least of 8 characters ")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$",
            message = "Password must have uppercase, lowercase, number and special character"
    ) String password) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.updateEmployeePassword(email,password);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete-tasks-project/{name}")
    public ResponseEntity<ResponseDTO>deleteAllTaskOfASpecificProject(@PathVariable @NotBlank(message = "Name Should Not be Null or Blank") String name) throws EmployeeManagementSystemException{
        ResponseDTO responseDTO=employeeManagementSystemService.deleteAllTaskOfASpecificProject(name);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

}
