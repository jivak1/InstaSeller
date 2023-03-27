package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.enums.Niche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NicheRepository extends JpaRepository<NicheEntity, Long> {
    public NicheEntity findByNiche(Niche niche) ;
}
