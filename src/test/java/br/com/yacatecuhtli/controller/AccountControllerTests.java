package br.com.yacatecuhtli.controller;

import br.com.yacatecuhtli.TestSecurityConfiguration;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.message.ErrorMessageJson;
import br.com.yacatecuhtli.core.message.MessageService;
import br.com.yacatecuhtli.domain.account.AccountController;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.account.AccountMessageCode;
import br.com.yacatecuhtli.domain.account.AccountService;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest({AccountController.class, TestSecurityConfiguration.class})
public class AccountControllerTests extends AbstractControllerTests {

    @MockBean
    private MessageService messageService;

    @MockBean
    private AccountService accountService;

    @Test
    public void shouldSendGetRequest() throws Exception {
        List<AccountJson> resultSet = createObjectList(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE, 5);
        BDDMockito.given(this.accountService.findAll()).willReturn(resultSet);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultSet));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/v1/accounts").accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendGetWithPathParameterRequest() throws Exception {
    	AccountJson result = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
    	BDDMockito.given(this.accountService.findOne(1)).willReturn(result);
    	String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
    	this.getMvc().perform(MockMvcRequestBuilders.get("/api/v1/accounts/{accountId}", 1).accept(getContentType()))
    		.andExpect(MockMvcResultMatchers.status().isOk())
    		.andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendPostRequest() throws Exception {
    	AccountJson shouldPersist = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
    	BDDMockito.given(this.accountService.save(shouldPersist)).willReturn(shouldPersist);
    	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/accounts")
    		.content(new ObjectMapper().writeValueAsString(shouldPersist))
    		.contentType(getContentType());
    	String responseJson = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(shouldPersist));
    	this.getMvc()
    		.perform(request)
    		.andExpect(MockMvcResultMatchers.status().isCreated())
    		.andExpect(MockMvcResultMatchers.content().json(responseJson));
    }

    @Test
    public void shouldSendPutRequest() throws Exception {
    	AccountJson shouldUpdate = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
    	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/v1/accounts/{accountId}", 1)
    		.content(new ObjectMapper().writeValueAsString(shouldUpdate))
    		.contentType(getContentType());
    	this.getMvc()
    		.perform(request)
    		.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldSendDeleteRequest() throws Exception {
    	this.getMvc().perform(MockMvcRequestBuilders.delete("/api/v1/accounts/{accountId}", 1))
    		.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
