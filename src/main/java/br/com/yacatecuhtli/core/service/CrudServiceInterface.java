package br.com.yacatecuhtli.core.service;

import br.com.yacatecuhtli.core.json.JsonRepresentation;

public interface CrudServiceInterface<J extends JsonRepresentation> extends ServiceInterface {

    J save(J object);

    void update(Integer objectId, J object);

    void destroy(Integer objectId);

    J findOne(Integer objectId);

}
