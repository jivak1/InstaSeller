package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email) ;
    public UserEntity findByUsername(String username) ;
}
