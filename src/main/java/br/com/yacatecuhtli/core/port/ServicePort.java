package br.com.yacatecuhtli.core.port;

import org.springframework.data.domain.Pageable;

import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.service.ServiceInterface;

public interface ServicePort<J extends JsonRepresentation> extends ServiceInterface {

	J save(J jsonRepresentation);
	
	void update(Integer entityId, J jsonRepresentation);
	
	void destroy(Integer entityId);
	
	J findOne(Integer entityId);
	
	JsonPagedResponse<J> findAll(Pageable pageable);
	
}
