package br.com.yacatecuhtli.controller;

import br.com.yacatecuhtli.TestSecurityConfiguration;
import br.com.yacatecuhtli.core.json.JsonResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.message.MessageService;
import br.com.yacatecuhtli.domain.account.AccountController;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.account.AccountService;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    public void shouldSendGetWithPathParameterRequest() throws Exception {

    }

    public void shouldSendPostRequest() throws Exception {

    }

    public void shouldSendPutRequest() throws Exception {

    }

    public void shouldSendDeleteRequest() throws Exception {

    }

}
