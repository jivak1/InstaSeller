package com.softuni.instaSeller.model.entity;

import com.softuni.instaSeller.model.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import jakarta.persistence.* ;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class AuthorityEntity extends BaseEntity {

    public AuthorityEntity(Authority authority)
    {
        this.authority = authority ;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority ;

    @OneToMany(mappedBy = "authority")
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<UserEntity> users ;

    public String getAuthorityName()
    {
        return this.authority.name() ;
    }
}
