package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import org.springframework.beans.factory.annotation.Autowired;
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

    protected ResponseEntity<JsonResponse> withJson(Object result, HttpStatus status) {
        return withJson(result, null, status);
    }

    protected ResponseEntity<JsonResponse> withJson(Object result, JsonResponseMetadata metadata, HttpStatus status) {
        return ResponseEntity.status(status).body(JsonResponseFactory.create(result, metadata));
    }

}
