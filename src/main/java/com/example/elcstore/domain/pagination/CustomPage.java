package com.example.elcstore.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class CustomPage<T> {
    private List<T> content;
    private CustomPageableDto pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageableDto(page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(), page.getTotalElements());
    }

    @Setter
    @Getter
    @AllArgsConstructor
    static class CustomPageableDto {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
    }
}