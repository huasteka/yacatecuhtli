package br.com.yacatecuhtli.domain.entry.execute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryExecutionJson {

    private Integer entryId;

    private Integer budgetCategoryId;

    private BigDecimal addition;

    private BigDecimal discount;

    private Date executeAt;

}
