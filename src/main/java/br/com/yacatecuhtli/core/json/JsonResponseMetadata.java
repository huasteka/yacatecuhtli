package br.com.yacatecuhtli.core.json;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JsonResponseMetadata {

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalItems;

}
