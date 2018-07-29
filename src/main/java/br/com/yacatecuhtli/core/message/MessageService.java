package br.com.yacatecuhtli.core.message;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {

    private static ThreadLocal<Map<String, JsonErrorMessageHolder>> messageHolder = new ThreadLocal<>();

    private ReloadableResourceBundleMessageSource messageSource;

    public MessageService(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void addMessage(String messageKey, Object... parameters) {
        getMessageHolder().put(messageKey, JsonErrorMessageHolder.createInstance(messageKey, parameters));
    }

    public String getMessage(String messageKey, Object... parameters) {
        return getMessageSource().getMessage(messageKey, parameters, LocaleContextHolder.getLocale());
    }

    public JsonErrorMessage getMessage(ErrorMessageCode errorCode, Object... parameters) {
        String message = getMessage(errorCode.getMessageKey(), parameters);
        return new JsonErrorMessage(errorCode.getMessageKey(), message);
    }

    public void addMessageList(List<JsonErrorMessageHolder> messageList) {
        for (JsonErrorMessageHolder message : messageList) {
            getMessageHolder().put(message.getErrorMessage().getMessageKey(), message);
        }
    }

    public List<JsonErrorMessage> getMessageList() {
        return messageHolder.get() == null
                ? Collections.emptyList()
                : messageHolder.get().entrySet().stream().map(this::getMessage).collect(Collectors.toList());
    }

    public Map<String, JsonErrorMessageHolder> getMessageHolder() {
        if (messageHolder.get() == null) {
            messageHolder.set(new LinkedHashMap<>());
        }
        return messageHolder.get();
    }

    public void clearMessageHolder() {
        if (messageHolder.get() != null) {
            messageHolder.get().clear();
        }
    }

    protected JsonErrorMessage getMessage(Map.Entry<String, JsonErrorMessageHolder> entry) {
        String messageContent = getMessage(entry.getKey(), entry.getValue().getParameters());
        JsonErrorMessage alertMessage = entry.getValue().getErrorMessage();
        alertMessage.setMessage(messageContent);
        return alertMessage;
    }

    protected ReloadableResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

}
