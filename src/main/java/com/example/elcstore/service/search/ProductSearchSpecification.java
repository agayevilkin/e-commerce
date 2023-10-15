package com.example.elcstore.service.search;

import com.example.elcstore.domain.Brand;
import com.example.elcstore.domain.Product;
import com.example.elcstore.domain.TechnicalCharacteristic;
import com.example.elcstore.domain.enums.SearchOperation;
import com.example.elcstore.dto.search.SearchCriteria;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchSpecification implements Specification<Product> {
    // TODO: 10/1/2023 optimize ProductSearchSpecification service and write for generic using

    private final List<SearchCriteria> criteriaList;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate[] predicates = criteriaList.stream()
                .map(criteria -> buildPredicateForFilteredSearchProduct(criteria, root, criteriaBuilder))
                .toArray(Predicate[]::new);
        return criteriaBuilder.and(predicates);
    }

    private Predicate buildPredicateForFilteredSearchProduct(SearchCriteria criteria, Root<Product> root, CriteriaBuilder criteriaBuilder) {
        SearchOperation searchOperation = criteria.getSearchOperation();
        String key = criteria.getKey();
        Object value = criteria.getValue();

        switch (searchOperation) {
            case EQUAL -> {
                if (isRelationalClassField(TechnicalCharacteristic.class, key)) {
                    return buildEqualPredicate(root.join("technicalCharacteristic", JoinType.LEFT).get(key), value, criteriaBuilder);
                } else if (isRelationalClassField(Brand.class, key)) {
                    return buildEqualPredicate(root.join("brand", JoinType.LEFT).get(key), value, criteriaBuilder);
                } else if (isRelationalClassField(Product.class, key)) {
                    return buildEqualPredicate(root.get(key), value, criteriaBuilder);
                } else return criteriaBuilder.conjunction();
            }
            case GREATER_THAN -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString());
            }
            case LESS_THAN -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString());
            }
        }
        return null;
    }

    public Specification<Product> buildSpecificaationForQuerySearch(String query) {
        return Specification.where(categoryNameLike(query)).or(nameLike(query).and(isStatusTrue()));
    }

    private Predicate buildEqualPredicate(Expression<?> expression, Object value, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(expression, value);
    }

    private Specification<Product> categoryNameLike(String query) {
        return (root, query1, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("categories", JoinType.LEFT).get("categoryName")), "%" + query.toLowerCase() + "%");
    }

    private Specification<Product> nameLike(String query) {
        return (root, query1, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%");
    }

    private Specification<Product> isStatusTrue() {
        return (root, query1, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("status"));
    }

    private <T> boolean isRelationalClassField(Class<T> relationalClass, String fieldName) {
        Field[] fields = relationalClass.getDeclaredFields();
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) return true;
        }
        return false;
    }
}
