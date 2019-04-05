package br.com.yacatecuhtli.core.port;

import org.springframework.http.ResponseEntity;

import br.com.yacatecuhtli.core.controller.RequestPagination;
import br.com.yacatecuhtli.core.json.JsonRepresentation;

public interface ControllerPort<RQ extends JsonRepresentation, RS extends ResponsePort> {

	ResponseEntity<RS> save(RQ jsonObject);

	ResponseEntity<RS> update(Integer id, RQ jsonObject);

	ResponseEntity<RS> destroy(Integer id);

	ResponseEntity<RS> findOne(Integer id);

	ResponseEntity<RS> findAll(RequestPagination pagination);

}
