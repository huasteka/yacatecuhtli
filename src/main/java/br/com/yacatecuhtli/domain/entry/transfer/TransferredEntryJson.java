package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
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
public class TransferredEntryJson implements JsonRepresentation {

    private Integer id;

    private EntryJson source;

    private EntryJson target;

    private Date transferredAt;

}
