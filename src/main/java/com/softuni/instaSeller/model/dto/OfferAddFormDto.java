package com.softuni.instaSeller.model.dto;

import com.softuni.instaSeller.model.entity.PageEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferAddFormDto {
    @Positive
    private double price ;

    @NotEmpty
    private String pageName ;

    private PageEntity page ;
}
