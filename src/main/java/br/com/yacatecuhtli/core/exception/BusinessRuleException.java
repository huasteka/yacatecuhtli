package br.com.yacatecuhtli.core.exception;

import br.com.yacatecuhtli.core.message.JsonErrorMessageHolder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessRuleException extends RuntimeException {

    @Getter
    private List<JsonErrorMessageHolder> errors = new ArrayList<>();

    public BusinessRuleException(ErrorMessageCode errorMessage, Object... parameters) {
        super();
        this.addMessage(errorMessage, parameters);
    }

    public BusinessRuleException addMessage(ErrorMessageCode errorMessage, Object... parameters) {
        this.errors.add(JsonErrorMessageHolder.createInstance(errorMessage.getMessageKey(), parameters));
        return this;
    }

    public BusinessRuleException addMessage(Supplier<Boolean> objectValidation, ErrorMessageCode errorMessage, Object... parameters) {
        if (Boolean.TRUE.equals(objectValidation.get())) {
            return addMessage(errorMessage, parameters);
        }
        return this;
    }

    public void throwException() {
        if (!this.errors.isEmpty()) {
            throw this;
        }
    }

}
