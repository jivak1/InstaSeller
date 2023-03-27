package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {
    public PageEntity findByName(String name) ;
}
