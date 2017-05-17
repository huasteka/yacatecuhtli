package br.com.yacatecuhtli.core.message;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {

    private static ThreadLocal<Map<String, ErrorMessageHolder>> messageHolder = new ThreadLocal<>();

    private ReloadableResourceBundleMessageSource messageSource;

    public MessageService(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void addMessage(String messageKey, Object... parameters) {
        getMessageHolder().put(messageKey, ErrorMessageHolder.createInstance(messageKey, parameters));
    }

    public void addMessageList(List<ErrorMessageHolder> messageList) {
        for (ErrorMessageHolder message : messageList) {
            getMessageHolder().put(message.getErrorMessage().getMessageKey(), message);
        }
    }

    public String getMessage(String messageKey, Object... parameters) {
        return getMessageSource().getMessage(messageKey, parameters, LocaleContextHolder.getLocale());
    }

    public List<ErrorMessageJson> getMessageList() {
        return messageHolder.get() == null
                ? Collections.emptyList()
                : messageHolder.get().entrySet().stream().map(this::getMessage).collect(Collectors.toList());
    }

    protected ErrorMessageJson getMessage(Map.Entry<String, ErrorMessageHolder> entry) {
        String messageContent = getMessage(entry.getKey(), entry.getValue().getParameters());
        ErrorMessageJson alertMessage = entry.getValue().getErrorMessage();
        alertMessage.setMessage(messageContent);
        return alertMessage;
    }

    public void clearMessageHolder() {
        if (messageHolder.get() != null) {
            messageHolder.get().clear();
        }
    }

    protected Map<String, ErrorMessageHolder> getMessageHolder() {
        if (messageHolder.get() == null) {
            messageHolder.set(new LinkedHashMap<>());
        }
        return messageHolder.get();
    }

    protected ReloadableResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

}
