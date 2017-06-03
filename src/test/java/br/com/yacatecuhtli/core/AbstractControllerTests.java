package br.com.yacatecuhtli.core;

import br.com.yacatecuhtli.AbstractApplicationTests;
import br.com.yacatecuhtli.core.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

public class AbstractControllerTests extends AbstractApplicationTests {

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.defaultCharset());

    @MockBean
    protected MessageService messageService;

    @Autowired
    protected MockMvc mvc;

    protected MediaType getContentType() {
        return contentType;
    }

    protected MockMvc getMvc() {
        return mvc;
    }

}
