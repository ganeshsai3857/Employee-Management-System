package com.gl.ems.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;

    @NotBlank(message = "Name Should Not be Null or Blank")
    @Pattern(regexp = "[A-Z][]a-z]+\\s[A-Z][a-z]+",message = "Name Should be exactly 2 words!")
    private String name;

    @Email(message = "Email Should be in Valid Format!!!")
    private String email;

    @NotNull(message = "LoginCredentialsDTO should not be Null")
    @Valid
    private LoginCredentialsDTO loginCredentialsDTO;

}
