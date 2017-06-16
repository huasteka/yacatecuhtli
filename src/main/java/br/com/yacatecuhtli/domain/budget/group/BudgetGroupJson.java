package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BudgetGroupJson implements JsonRepresentation {

    protected Integer id;

    protected String name;

}
