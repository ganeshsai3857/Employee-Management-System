package com.gl.ems.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {

    private Integer id;

    @NotBlank(message = "Name Should Not be Null or Blank")
    private String name;

    @NotBlank(message = "Location Should Not be Null or Blank")
    private String location;

}
