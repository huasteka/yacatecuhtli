package br.com.yacatecuhtli.core.validator;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.port.ValidatorPort;

public abstract class CrudValidator<J extends JsonRepresentation> extends VoidValidator<J> implements ValidatorPort<J> {

}
