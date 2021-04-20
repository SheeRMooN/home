package com.example.criteria.home.controller;

import com.example.criteria.home.model.Home;
import com.example.criteria.home.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private HomeService service;

    @GetMapping("/{name}")
    public List<Home> getHome(@PathVariable String name){
        return service.getHome(name);
    }

    @GetMapping("/search/{name}")
    public List<Home> searchAll(@PathVariable String name){
        return service.getAny(name);
    }
    @GetMapping("/and/{name}/{type}")
    public List<Home> getNameAndType(@PathVariable String name, @PathVariable String type){
        return service.getNameAndType(name,type);
    }
}
