package br.com.yacatecuhtli.core.port;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.validator.Validator;

public interface ValidatorPort<J extends JsonRepresentation> extends Validator<J, Void> {
	
	void exists(Integer entityId) throws BusinessRuleException;
	
}
