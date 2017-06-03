package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.AbstractControllerTests;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
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
@WebMvcTest(value = {AccountController.class}, secure = false)
public class AccountControllerTests extends AbstractControllerTests {

    @MockBean
    private AccountService accountService;

    @Test
    public void shouldSendGetRequest() throws Exception {
        List<AccountJson> resultSet = createObjectList(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE, 5);
        BDDMockito.given(this.accountService.findAll()).willReturn(resultSet);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultSet));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/accounts").accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendGetWithPathParameterRequest() throws Exception {
        int accountId = 1;
        AccountJson result = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        BDDMockito.given(this.accountService.findOne(accountId)).willReturn(result);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/accounts/{accountId}", accountId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendPostRequest() throws Exception {
        AccountJson payload = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        BDDMockito.given(this.accountService.save(payload)).willReturn(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/accounts").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

    @Test
    public void shouldSendPutRequest() throws Exception {
        int accountId = 1;
        AccountJson payload = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        this.getMvc().perform(MockMvcRequestBuilders.put("/api/accounts/{accountId}", accountId).contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldSendDeleteRequest() throws Exception {
        int accountId = 1;
        this.getMvc().perform(MockMvcRequestBuilders.delete("/api/accounts/{accountId}", accountId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
