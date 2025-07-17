package com.gl.ems.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginCredentialsDTO {
    private Integer id;

    @NotBlank(message = "Username Should not be Null or Blank")
    @Size(min = 4,max = 20,message = "Username should be of Range 4-20")
    private String username;

    @NotBlank(message = "Password Should not be Null or Blank")
    @Size(min = 8,message = "password must be at-least of 8 characters ")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$",
            message = "Password must have uppercase, lowercase, number and special character"
    )
    private String password;


}
