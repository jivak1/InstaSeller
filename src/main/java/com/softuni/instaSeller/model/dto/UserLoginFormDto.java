package com.softuni.instaSeller.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.* ;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginFormDto {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String username ;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String password ;


}
