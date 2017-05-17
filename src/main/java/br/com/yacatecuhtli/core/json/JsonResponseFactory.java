package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.message.ErrorMessageJson;

import java.util.List;

public class JsonResponseFactory {

    public static JsonResponse create(Object result) {
        return create(result, null);
    }

    public static JsonResponse create(Object result, JsonResponseMetadata metadata) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setResult(result);
        jsonResponse.setMeta(metadata);
        return jsonResponse;
    }

    public static JsonResponse create(List<ErrorMessageJson> errors) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setErrors(errors);
        return jsonResponse;
    }

}
