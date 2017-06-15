package br.com.yacatecuhtli.core.controller;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RequestPagination {

    protected Integer pageSize;

    protected Integer currentPage;

}
