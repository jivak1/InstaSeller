package com.softuni.instaSeller.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pages")
public class PageEntity extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name ;

    @Column(name = "followers", nullable = false)
    private int followers ;

    @Column(name = "following", nullable = false)
    private int following ;

    @Column(name = "image", nullable = false)
    private String imageURL ;

    @ManyToOne
    @JoinColumn(name = "niche_id", referencedColumnName = "id")
    private NicheEntity niche ;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner ;

    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @OneToMany(mappedBy = "page")
    private List<OfferEntity> offers ;
}
