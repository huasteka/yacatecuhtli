package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.json.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractRestController {

    protected ResponseEntity<JsonResponse> withJson() {
        return withJson(HttpStatus.OK);
    }

    protected ResponseEntity<JsonResponse> withJson(HttpStatus status) {
        return withJson(null, null, status);
    }

    protected ResponseEntity<JsonResponse> withJson(Object result) {
        return withJson(result, null, HttpStatus.OK);
    }

    protected ResponseEntity<JsonResponse> withJson(Object result, JsonResponseMetadata metadata) {
        return withJson(result, metadata, HttpStatus.OK);
    }

    protected <J extends JsonRepresentation> ResponseEntity<JsonResponse> withJson(JsonPagedResponse<J> pagedResponse) {
        return withJson(pagedResponse.getResult(), pagedResponse.getMeta());
    }

    protected ResponseEntity<JsonResponse> withJson(Object result, HttpStatus status) {
        return withJson(result, null, status);
    }

    protected ResponseEntity<JsonResponse> withJson(Object result, JsonResponseMetadata metadata, HttpStatus status) {
        return ResponseEntity.status(status).body(JsonResponseFactory.create(result, metadata));
    }

}
