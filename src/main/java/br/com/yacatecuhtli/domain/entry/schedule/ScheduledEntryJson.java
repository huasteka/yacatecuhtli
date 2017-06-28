package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryJson;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledEntryJson implements JsonRepresentation {

    private Integer id;

    private EntryJson entry;

    private BudgetCategoryJson category;

    private Date executeAt;

}
