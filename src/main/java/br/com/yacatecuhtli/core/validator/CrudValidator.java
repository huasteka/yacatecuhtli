package br.com.yacatecuhtli.core.validator;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.json.JsonRepresentation;

public abstract class CrudValidator<J extends JsonRepresentation> extends VoidValidator<J> {

    public abstract void exists(Integer entityId) throws BusinessRuleException;

}
