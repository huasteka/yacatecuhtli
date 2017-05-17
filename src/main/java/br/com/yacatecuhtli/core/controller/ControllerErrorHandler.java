package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ControllerErrorHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public JsonResponse handleValidationError(MethodArgumentNotValidException exception) {
        return handleFieldError(exception.getBindingResult().getFieldErrors());
    }

    private JsonResponse handleFieldError(List<FieldError> fieldError) {
        Optional.ofNullable(fieldError).orElseGet(ArrayList::new).stream().forEach(this::addError);
        return withJson();
    }

    private void addError(FieldError fieldError) {
        messageService.addMessage(fieldError.getDefaultMessage(), fieldError.getArguments());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public JsonResponse handleValidationError(BusinessRuleException exception) {
        messageService.addMessageList(exception.getErrors());
        return withJson();
    }

    private JsonResponse withJson() {
        try {
            return JsonResponseFactory.create(messageService.getMessageList());
        } finally {
            messageService.clearMessageHolder();
        }
    }

}
