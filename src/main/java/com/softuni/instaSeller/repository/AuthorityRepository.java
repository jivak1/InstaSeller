package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.enums.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    public AuthorityEntity findByAuthority(Authority authority) ;
}
