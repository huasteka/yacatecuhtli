package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.message.JsonErrorMessage;

import java.util.List;

public class JsonResponseFactory {

    public static JsonResponse create(Object result) {
        return create(result, null);
    }

    public static JsonResponse create(Object result, JsonResponseMetadata metadata) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(result);
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

}
