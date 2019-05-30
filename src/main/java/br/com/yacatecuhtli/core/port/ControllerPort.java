package br.com.yacatecuhtli.core.port;

import org.springframework.http.ResponseEntity;

import br.com.yacatecuhtli.core.controller.RequestPagination;
import br.com.yacatecuhtli.core.json.JsonRepresentation;

public interface ControllerPort<R extends JsonRepresentation, S extends ResponsePort> {

	ResponseEntity<S> save(R jsonObject);

	ResponseEntity<S> update(Integer id, R jsonObject);

	ResponseEntity<S> destroy(Integer id);

	ResponseEntity<S> findOne(Integer id);

	ResponseEntity<S> findAll(RequestPagination pagination);

}
