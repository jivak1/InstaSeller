package com.softuni.instaSeller.model.entity;

import com.softuni.instaSeller.model.enums.Niche;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.* ;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "niches")
public class NicheEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "niche", nullable = false)
    private Niche niche ;

    @OneToMany(mappedBy = "niche")
    private List<PageEntity> pages  ;

    public NicheEntity(Niche niche)
    {
        this.niche = niche ;
    }

    public String getNicheName()
    {
        return this.niche.name() ;
    }
}
