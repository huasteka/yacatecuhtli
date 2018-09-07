package br.com.yacatecuhtli.domain;

import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.message.JsonErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonResponse error() {
        JsonErrorMessage errorMessage = new JsonErrorMessage(SystemMessageCode.SYSTEM_NOT_FOUND_ERROR.getMessageKey());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        return JsonResponseFactory.create(errorMessage);
    }

}
