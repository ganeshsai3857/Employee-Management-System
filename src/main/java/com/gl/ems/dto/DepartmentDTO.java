package com.gl.ems.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;

    @NotBlank(message = "Name Should Not be Null or Blank")
    private String name;

//    @NotEmpty(message = "ProjectDTO Should Not be Null or Empty")
    @Valid
    private List<ProjectDTO>projectDTOS;
}
