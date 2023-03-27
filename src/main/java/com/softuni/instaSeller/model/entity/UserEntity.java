package com.softuni.instaSeller.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(name = "username", nullable = false)
    private String username ;

    @Column(name = "password", nullable = false)
    private String password ;

    @Column(name = "email", nullable = false)
    private String email ;

    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @OneToMany(mappedBy = "owner")
    private List<PageEntity> pages ;

    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @OneToMany(mappedBy = "seller")
    private List<OfferEntity> offers ;

    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private AuthorityEntity authority ;

    public UserEntity(String username, String password, String email, AuthorityEntity authority)
    {
        this.username = username ;
        this.password = password ;
        this.email = email ;
        this.authority = authority ;
    }

}
