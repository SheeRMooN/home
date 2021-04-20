package com.example.criteria.home.repository;

import com.example.criteria.home.model.Home;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends CrudRepository<Home, Long>, JpaSpecificationExecutor<Home> {
    @Override
    List<Home> findAll(Specification<Home> specification);

    @Override
    Optional<Home> findOne(Specification<Home> specification);

    @Override
    long count(Specification<Home> specification);

    void flush();

    void delete(Home home);

    Home save(Home home);
}
