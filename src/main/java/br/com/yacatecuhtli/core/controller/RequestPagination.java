package br.com.yacatecuhtli.core.controller;

import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RequestPagination {

    protected static final Integer DEFAULT_PAGE_SIZE = 10;

    protected static final Integer DEFAULT_CURRENT_PAGE = 0;

    protected Integer pageSize;

    protected Integer currentPage;

    public Integer getPageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage == null ? DEFAULT_CURRENT_PAGE : currentPage;
    }
}
