package com.gl.ems.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TaskDTO {

    private Integer id;

    @NotBlank(message = "Title Should Not be Null or Blank")
    private String title;

    @NotNull(message = "Status Should not be Null")
    private String status;

    @FutureOrPresent(message = "StartDate Should be of Present or Future!!")
    private LocalDate startDate;

    @Future(message = "EndDate Should be of Future")
    private LocalDate endDate;

    @NotNull(message = "ProjectDTO Should Not be Null or Empty")
    @Valid
    private ProjectDTO projectDTO;
}
