package br.com.yacatecuhtli.core.json;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JsonPagedResponse<J extends JsonRepresentation> {

    protected List<J> result;

    protected JsonResponseMetadata meta;

}
