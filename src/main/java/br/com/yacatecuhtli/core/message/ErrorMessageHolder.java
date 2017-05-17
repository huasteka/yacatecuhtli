package br.com.yacatecuhtli.core.message;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ErrorMessageHolder {

    protected ErrorMessageJson errorMessage;
    protected Object[] parameters;

    public static ErrorMessageHolder createInstance(String messageKey, Object[] parameters) {
        return new ErrorMessageHolder(new ErrorMessageJson(messageKey), parameters);
    }

}
