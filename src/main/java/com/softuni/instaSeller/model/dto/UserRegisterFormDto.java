package com.softuni.instaSeller.model.dto;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.vallidation.email.EmailValidate;
import com.softuni.instaSeller.vallidation.password.PasswordValidate;
import com.softuni.instaSeller.vallidation.username.UsernameRegisterValidate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EmailValidate
@PasswordValidate
@UsernameRegisterValidate
public class UserRegisterFormDto {
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username ;

    @NotEmpty
    @Email
    private String email ;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String password ;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String confirmPassword ;

    @NotEmpty
    private String authorityName ;

    private AuthorityEntity authority ;
}
