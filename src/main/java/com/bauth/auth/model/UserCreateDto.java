package com.bauth.auth.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bauth.auth.validation.PasswordConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDto implements Serializable {
    @Valid
    @NotBlank
    @Email
    private final String username;

    /*
     * @Valid
     * 
     * @NotBlank
     * 
     * @Email
     * private final String email;
     */

    @Valid
    @NotBlank
    @Size(min = 2, max = 25)
    private final String firstName;

    @Valid
    @Size(min = 2, max = 25)
    private final String lastName;

    private final String phoneNumber;

    private final String imageUrl;

    @Valid
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")
    private final String password;

    @Valid
    @PasswordConstraint
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")
    private final String confirmPassword;
}
