package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BudgetCategoryJson implements JsonRepresentation {

    protected Integer id;

    protected String name;

    protected BudgetGroupJson group;

}
