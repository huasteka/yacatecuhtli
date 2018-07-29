package br.com.yacatecuhtli.domain.account;

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
public class AccountJson implements JsonRepresentation {

    protected Integer id;

    protected String code;

    protected String name;

}
