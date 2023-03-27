package com.softuni.instaSeller.model.dto;

import com.softuni.instaSeller.model.entity.NicheEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageAddFormDto {
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name ;

    @Positive
    private int followers ;

    @Positive
    private int following ;

    private NicheEntity niche ;

    @NotEmpty
    private String nicheName ;

    @NotEmpty
    private String imageURL ;
}
