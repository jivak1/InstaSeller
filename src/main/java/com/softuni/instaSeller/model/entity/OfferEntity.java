package com.softuni.instaSeller.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.* ;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Column(name = "price", nullable = false)
    private double price ;

    @ManyToOne
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private PageEntity page ;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private UserEntity seller ;
}
