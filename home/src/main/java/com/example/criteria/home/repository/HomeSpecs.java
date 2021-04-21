package com.example.criteria.home.repository;

import com.example.criteria.home.model.Home;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@AllArgsConstructor
@Component
public final class HomeSpecs {
    EntityManager entityManager;

    public static Specification<Home> getHomeByNameSpec(String name) {
        return (root, query, cb) -> {
            return cb.equal(root.get("name"), name);
        };
    }

    public static Specification<Home> searchOne(String searchTerm) {
        return (root, query, cb) -> {
            return cb.or(
                    cb.like(cb.lower(root.<String>get("title")), searchTerm),
                    cb.like(cb.lower(root.<String>get("type")), searchTerm),
                    cb.like(cb.lower(root.<String>get("name")), searchTerm)
            );
        };
    }
    public static Specification<Home> getNameAndType(String name, String type){
        return (root, criteriaQuery, cb) -> {
          return cb.and(
                  cb.like(root.<String>get("name"),name),
                  cb.like(root.<String>get("type"), type)
          );
        };
    }
    public  List<Home> searchName(String search) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Home> criteria = cb.createQuery(Home.class);
        Root<Home> homeRoot = criteria.from(Home.class);
        criteria.select(homeRoot);
        criteria.where(cb.equal(homeRoot.get("name"), search));
        return entityManager.createQuery(criteria).getResultList();
    }
    public  List<Home> searchAllName() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Home> criteria = cb.createQuery(Home.class);
        Root<Home> homeRoot = criteria.from(Home.class);
        criteria.select(homeRoot);
        return entityManager.createQuery(criteria).getResultList();
    }
    public  List<String> searchAllNameString() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteria = cb.createQuery(String.class);
        Root<String> homeRoot = criteria.from(String.class);
        criteria.select(homeRoot);
        return entityManager.createQuery(criteria).getResultList();
    }










    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
