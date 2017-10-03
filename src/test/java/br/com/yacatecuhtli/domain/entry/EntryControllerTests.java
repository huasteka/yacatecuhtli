package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import br.com.yacatecuhtli.domain.entry.revert.ReversedEntryService;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

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

    @Test
    public void shouldSendGetRequestWithPagination() throws Exception {
        List<EntryJson> entryList = createObjectList(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE, 15);
        JsonPagedResponse<EntryJson> result = new JsonPagedResponse<>(entryList.subList(10, 14), new JsonResponseMetadata(10, 1, 2, 15L));
        BDDMockito.given(this.entryService.findByAccount(1, new PageRequest(1, 10))).willReturn(result);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/entries/accounts/{accountId}", 1)
                .contentType(getContentType())
                .param("currentPage", "1");
        this.getMvc().perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

}
