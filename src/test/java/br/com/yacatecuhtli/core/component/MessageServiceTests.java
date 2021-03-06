package br.com.yacatecuhtli.core.component;

import br.com.yacatecuhtli.core.AbstractCoreSpec;
import br.com.yacatecuhtli.core.message.JsonErrorMessage;
import br.com.yacatecuhtli.core.message.MessageService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.List;

public class MessageServiceTests extends AbstractCoreSpec {

    private MessageService messageService;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageService = new MessageService(messageSource);
    }

    @Test
    public void shouldAddMessage() {
        messageService.addMessage("message_test_key");
        messageService.addMessage("message_test_key_2");
        messageService.addMessage("message_test_key_3");
        Assert.assertThat(messageService.getMessageHolder().size(), Matchers.equalTo(3));

    }

    @Test
    public void shouldGetMessage() {
        messageService.addMessage("message_test_key");
        List<JsonErrorMessage> errors = messageService.getMessageList();
        Assert.assertThat(errors.get(0).getMessage(), Matchers.equalTo("This is a test"));
    }

    @Test
    public void shouldCleanMessages() {
        messageService.addMessage("message_test_key");
        messageService.clearMessageHolder();
        Assert.assertThat(messageService.getMessageHolder().isEmpty(), Matchers.is(Boolean.TRUE));
    }

}
