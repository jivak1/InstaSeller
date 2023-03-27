package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    public List<OfferEntity> findAllBySellerNot(UserEntity seller) ;

    public List<OfferEntity> findAllByPage(PageEntity page) ;
}
