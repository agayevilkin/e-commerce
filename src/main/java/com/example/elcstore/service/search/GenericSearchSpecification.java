package com.example.elcstore.service.search;

import com.example.elcstore.domain.enums.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GenericSearchSpecification<T> implements Specification<T> {

    // TODO: 9/30/2023 change criteria to List
    // TODO: 10/1/2023 optimize GenericSearchSpecification service

    private final SearchCriteria criteria;

    public GenericSearchSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate finalPredicate = builder.conjunction();

        Predicate individualPredicate = buildPredicate(criteria, root, builder);
        finalPredicate = builder.and(finalPredicate, individualPredicate);

        return finalPredicate;
    }

    private Predicate buildPredicate(SearchCriteria criteria, Root<T> root, CriteriaBuilder builder) {
        SearchOperation searchOperation = criteria.getSearchOperation();
        String key = criteria.getKey();
        Object value = criteria.getValue();

        if (searchOperation.equals(SearchOperation.GREATER_THAN)) {
            return builder.greaterThanOrEqualTo(root.get(key), value.toString());
        } else if (searchOperation.equals(SearchOperation.LESS_THAN)) {
            return builder.lessThanOrEqualTo(root.get(key), value.toString());
        } else if (searchOperation.equals(SearchOperation.EQUAL)) {
            return builder.equal(root.get(key), value);
        } else if (searchOperation.equals(SearchOperation.LIKE)) {
            if (root.get(key).getJavaType() == String.class) {
                return builder.like(builder.lower(root.get(key)), value.toString().toLowerCase() + "%");
            }
        }
        return null;
    }
}
