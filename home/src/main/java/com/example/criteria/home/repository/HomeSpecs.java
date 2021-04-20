package com.example.criteria.home.repository;

import com.example.criteria.home.model.Home;
import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@AllArgsConstructor
public final class HomeSpecs {

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
//    public static Specification<Home> seeqewq(String search){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("home-unit");
//        EntityManager em = emf.createEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//
//        //main query
//        CriteriaQuery<Home> employeeQuery = cb.createQuery(Home.class);
//        Root<Home> employee = employeeQuery.from(Home.class);
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
//        // equivalent JPQL
//        //"SELECT e FROM Employee e WHERE EXISTS (SELECT j from JobInfo j WHERE j.jobName = e.job)"
//
//
//
//        return null;
//    }














    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
