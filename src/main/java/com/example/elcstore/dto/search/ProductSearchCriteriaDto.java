package com.example.elcstore.dto.search;

import com.example.elcstore.service.search.SearchCriteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductSearchCriteriaDto {
    private List<SearchCriteria> criteriaList;
}
