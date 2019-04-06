package br.com.yacatecuhtli.core.json;

import java.util.Collections;
import java.util.List;

import br.com.yacatecuhtli.core.message.JsonErrorMessage;

public class JsonResponseFactory {

    public static JsonResponse create(Object result) {
        return create(result, null);
    }

    public static JsonResponse create(Object result, JsonResponseMetadata metadata) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setAttributes(result);
        jsonResponse.setMeta(metadata);
        return jsonResponse;
    }

    public static <J extends JsonRepresentation> JsonResponse create(JsonPagedResponse<J> result) {
        return create(result.getResult(), result.getMeta());
    }

    public static JsonResponse create(List<JsonErrorMessage> errors) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setErrors(errors);
        return jsonResponse;
    }

    public static JsonResponse create(JsonErrorMessage error) {
        return create(Collections.singletonList(error));
    }

}
