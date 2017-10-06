package br.com.yacatecuhtli.core.validator;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;

public interface Validator<O, R> {

    R validate(O object) throws BusinessRuleException;

}
