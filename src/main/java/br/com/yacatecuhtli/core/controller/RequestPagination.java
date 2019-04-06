package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.port.PaginationPort;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestPagination implements PaginationPort {

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
