package br.com.yacatecuhtli.core.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.port.ControllerPort;
import br.com.yacatecuhtli.core.port.ServicePort;

public abstract class ControllerAdapter<T extends JsonRepresentation> extends AbstractRestController implements ControllerPort<T, JsonResponse> {

	protected ServicePort<T> service;

	public ControllerAdapter(ServicePort<T> service) {
		this.service = service;
	}

	@PostMapping
	@Override
	public ResponseEntity<JsonResponse> save(@RequestBody T json) {
		return withJson(service.save(json), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<JsonResponse> update(@PathVariable Integer id, @RequestBody T json) {
		service.update(id, json);
		return withJson(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<JsonResponse> destroy(@PathVariable Integer id) {
		service.destroy(id);
        return withJson(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<JsonResponse> findOne(@PathVariable Integer id) {
		return withJson(service.findOne(id));
	}

	@GetMapping
	@Override
	public ResponseEntity<JsonResponse> findAll(RequestPagination pagination) {
		return withJson(service.findAll(new PageRequest(pagination.getCurrentPage(), pagination.getPageSize())));
	}

}
