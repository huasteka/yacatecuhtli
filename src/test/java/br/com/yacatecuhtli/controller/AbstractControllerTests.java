package br.com.yacatecuhtli.controller;

import br.com.yacatecuhtli.AbstractApplicationTests;
import br.com.yacatecuhtli.TestSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

public class AbstractControllerTests extends AbstractApplicationTests {

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.defaultCharset());

    @Autowired
    protected MockMvc mvc;

    public MediaType getContentType() {
        return contentType;
    }

    public MockMvc getMvc() {
        return mvc;
    }

}
