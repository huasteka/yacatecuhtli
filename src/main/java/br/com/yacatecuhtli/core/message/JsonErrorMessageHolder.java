package br.com.yacatecuhtli.core.message;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JsonErrorMessageHolder {

    protected JsonErrorMessage errorMessage;
    protected Object[] parameters;

    public static JsonErrorMessageHolder createInstance(String messageKey, Object[] parameters) {
        return new JsonErrorMessageHolder(new JsonErrorMessage(messageKey), parameters);
    }

}
