package com.example.criteria.home.service;

import com.example.criteria.home.model.Home;
import com.example.criteria.home.repository.HomeRepository;
import com.example.criteria.home.repository.HomeSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HomeService {
    private HomeRepository homeRepository;

    public Optional<Home> getHome(String name){
        Specification<Home> homeSpecification = HomeSpecs.getHomeByNameSpec(name);
        Optional<Home> home = homeRepository.findOne(homeSpecification);
        return home;
    }

    public Optional<Home> getAny(String name){
        Specification<Home> homeSpecification = HomeSpecs.searchOne(name);
        Optional<Home> all = homeRepository.findOne(homeSpecification);
        return all;
    }
    public Optional<Home> getNameAndType(String name, String type){
        Specification<Home> homeSP = HomeSpecs.getNameAndType( name, type);
        Optional<Home> result = homeRepository.findOne(homeSP);
        return result;
    }
}
