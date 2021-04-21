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
    public  List<Home> search(String search){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        //main query
        CriteriaQuery<Home> criteria = cb.createQuery(Home.class);
        Root<Home> homeRoot = criteria.from(Home.class);
        criteria.select(homeRoot);
        criteria.where(cb.equal(homeRoot.get("name"),search));
        return  entityManager.createQuery(criteria).getResultList();

//        //subquery
//        Subquery<JobInfo> jobInfoSubquery = employeeQuery.subquery(JobInfo.class);
//        Root<JobInfo> jobInfo = jobInfoSubquery.from(JobInfo.class);
//        jobInfoSubquery.select(jobInfo)//subquery selection
//                .where(criteriaBuilder.equal(jobInfo.get(JobInfo_.jobName),
//                        employee.get(Employee_.job)));//subquery restriction
//        //main query selection
//        employeeQuery.select(employee)
//                .where(criteriaBuilder.exists(jobInfoSubquery));
//
//        TypedQuery<Employee> typedQuery = entityManager.createQuery(employeeQuery);
//        List<Employee> resultList = typedQuery.getResultList();
//        resultList.forEach(System.out::println);
//
//        entityManager.close();
        // equivalent JPQL
        //"SELECT e FROM Employee e WHERE EXISTS (SELECT j from JobInfo j WHERE j.jobName = e.job)"




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
