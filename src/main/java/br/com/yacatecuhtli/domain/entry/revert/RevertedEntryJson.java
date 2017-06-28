package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class RevertedEntryJson implements JsonRepresentation {

    private Integer id;

    private EntryJson revert;

    private EntryJson reverted;

}
