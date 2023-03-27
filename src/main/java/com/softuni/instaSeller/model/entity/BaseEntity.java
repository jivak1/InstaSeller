package com.softuni.instaSeller.model.entity;

import lombok.Getter;

import jakarta.persistence.* ;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
}
