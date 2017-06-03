package br.com.yacatecuhtli.core.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonErrorMessage {

    private String messageKey;
    private String message;

    public JsonErrorMessage(String messageKey) {
        this.messageKey = messageKey;
    }

}
