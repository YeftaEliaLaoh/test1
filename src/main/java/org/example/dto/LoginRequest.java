package org.example.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;
    @NotNull
    @Length(min = 4, max = 10)
    private String password;

}