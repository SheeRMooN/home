package com.example.criteria.home.service;

import com.example.criteria.home.model.Home;
import com.example.criteria.home.repository.HomeRepository;
import com.example.criteria.home.repository.HomeSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.criteria.home.repository.HomeSpecs.getHomeByNameSpec;
import static com.example.criteria.home.repository.HomeSpecs.searchOne;

@Service
@AllArgsConstructor
public class HomeService {
    private HomeRepository homeRepository;
    HomeSpecs homeSpecs;

    public List<Home> getSearch(String name){
        return homeSpecs.search(name);
    }


    public List<Home> getHome(String name){

        return homeRepository.findAll(getHomeByNameSpec(name));
    }

    public List<Home> getAny(String name){

        return homeRepository.findAll(searchOne(name));

    }
    public List<Home> getNameAndType(String name, String type){
//        Specification<Home> homeSP = HomeSpecs.getNameAndType( name, type);
//        Optional<Home> result = homeRepository.findOne(homeSP);
//        return result;
        return  homeRepository.findAll((Specification<Home>) getNameAndType(name, type));
    }

}
