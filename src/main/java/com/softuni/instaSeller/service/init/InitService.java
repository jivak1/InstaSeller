package com.softuni.instaSeller.service.init;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.model.enums.Niche;
import com.softuni.instaSeller.repository.AuthorityRepository;
import com.softuni.instaSeller.repository.NicheRepository;
import com.softuni.instaSeller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private AuthorityRepository authorityRepository ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private NicheRepository nicheRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @PostConstruct
    private void dbInit()
    {

        if(!isDbInit())
        {
            authorityInit();
            nicheInit();
        }
    }

    private boolean isDbInit()
    {
        return this.authorityRepository.count() > 0 ;
    }
    private void authorityInit()
    {
        List<AuthorityEntity> authorities = new ArrayList<>() ;

        authorities.add(new AuthorityEntity(Authority.USER)) ;
        authorities.add(new AuthorityEntity(Authority.ADMIN)) ;

        this.authorityRepository.saveAll(authorities) ;

    }
    private void nicheInit()
    {
        List<NicheEntity> niches = new ArrayList<>() ;

        niches.add(new NicheEntity(Niche.CAR)) ;
        niches.add(new NicheEntity(Niche.ANIMAL)) ;
        niches.add(new NicheEntity(Niche.FITNESS)) ;
        niches.add(new NicheEntity(Niche.TRAVEL)) ;

        this.nicheRepository.saveAll(niches) ;
    }
}
