package br.com.yacatecuhtli;

import br.com.yacatecuhtli.core.message.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.Charset;

@Configuration
public class MessageConfiguration {

    private static final String MESSAGE_SOURCE_BASENAME = "classpath:i18n/errors";

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE_BASENAME);
        messageSource.setDefaultEncoding(Charset.defaultCharset().displayName());
        return messageSource;
    }

    @Bean
    public MessageService messageService(ReloadableResourceBundleMessageSource messageSource) {
        return new MessageService(messageSource);
    }

}
