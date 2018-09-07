package br.com.yacatecuhtli.core.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    public JsonErrorMessage(String messageKey) {
        this.messageKey = messageKey;
    }

    public JsonErrorMessage(String messageKey, String message) {
        this(messageKey);
        this.message = message;
    }

}
