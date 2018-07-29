package br.com.yacatecuhtli.core.controller;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.message.JsonErrorMessage;
import br.com.yacatecuhtli.core.message.MessageService;
import br.com.yacatecuhtli.domain.SystemMessageCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private static final Logger LOGGER = Logger.getLogger(ControllerErrorHandler.class);

    @Autowired
    private MessageService messageService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<JsonResponse> handleValidationError(MethodArgumentNotValidException exception) {
        return handleFieldError(exception.getBindingResult().getFieldErrors());
    }

    private ResponseEntity<JsonResponse> handleFieldError(List<FieldError> fieldError) {
        Optional.ofNullable(fieldError).orElseGet(ArrayList::new).stream().forEach(this::addError);
        return withJson();
    }

    private void addError(FieldError fieldError) {
        messageService.addMessage(fieldError.getDefaultMessage(), fieldError.getArguments());
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<JsonResponse> handleValidationError(BusinessRuleException exception) {
        messageService.addMessageList(exception.getErrors());
        return withJson();
    }

    private ResponseEntity<JsonResponse> withJson() {
        try {
            JsonResponse response = JsonResponseFactory.create(messageService.getMessageList());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception exception) {
            return createErrorMessage(exception);
        } finally {
            messageService.clearMessageHolder();
        }
    }

    private ResponseEntity<JsonResponse> createErrorMessage(Exception exception) {
        LOGGER.debug(exception.getMessage(), exception);
        JsonErrorMessage errorMessage = messageService.getMessage(SystemMessageCode.SYSTEM_CONFIGURATION_ERROR);
        return ResponseEntity.badRequest().body(JsonResponseFactory.create(errorMessage));
    }

}
