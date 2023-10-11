package com.example.elcstore.dto.search;

import com.example.elcstore.domain.enums.SearchOperation;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria {
    private String key;
    private SearchOperation searchOperation;
    private Object value;
}