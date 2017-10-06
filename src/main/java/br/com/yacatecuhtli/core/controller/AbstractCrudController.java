package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.service.AbstractCrudService;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AbstractCrudController<J extends JsonRepresentation, E extends PersistentEntity<J>, C extends JsonConverter<J, E>, R extends EntityRepository<E>, V extends CrudValidator<J>, S extends AbstractCrudService<J, E, C, R, V>> extends AbstractRestController {

    protected S entityService;

    public AbstractCrudController(S entityService) {
        this.entityService = entityService;
    }

    @PostMapping
    public ResponseEntity<JsonResponse> save(@RequestBody J jsonObject) {
        return withJson(entityService.save(jsonObject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonResponse> update(@PathVariable Integer id, @RequestBody J jsonObject) {
        entityService.update(id, jsonObject);
        return withJson(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponse> destroy(@PathVariable Integer id) {
        entityService.destroy(id);
        return withJson(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResponse> findOne(@PathVariable Integer id) {
        return withJson(entityService.findOne(id));
    }

    @GetMapping
    public ResponseEntity<JsonResponse> findAll(RequestPagination pagination) {
        return withJson(entityService.findAll(new PageRequest(pagination.getCurrentPage(), pagination.getPageSize())));
    }

}
