package br.com.yacatecuhtli.core.validator;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;

public abstract class VoidValidator<O> implements Validator<O, Void> {

    @Override
    public Void validate(O object) throws BusinessRuleException {
        executeValidation(object);
        return null;
    }

    public abstract void executeValidation(O object) throws BusinessRuleException;

}
