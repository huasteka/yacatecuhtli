package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {EntryController.class}, secure = false)
public class EntryControllerTests extends AbstractControllerSpec {

    @MockBean
    private EntryService entryService;

    @Test
    public void shouldSendPostRequestToDeposit() throws Exception {
        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        BDDMockito.given(this.entryService.deposit(payload)).willReturn(payload);
        String json = new ObjectMapper().writeValueAsString(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/entries/deposit").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

    @Test
    public void shouldSendPostRequestToWithdraw() throws Exception {
        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        BDDMockito.given(this.entryService.withdraw(payload)).willReturn(payload);
        String json = new ObjectMapper().writeValueAsString(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/entries/withdraw").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

}
