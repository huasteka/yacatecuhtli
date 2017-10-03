package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.ExecuteEntryTemplateLoader;
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
@WebMvcTest(value = {ExecuteEntryController.class}, secure = false)
public class ExecuteEntryControllerTests extends AbstractControllerSpec {

    private static final Integer MOCK_ENTRY_ID = 1;

    @MockBean
    private ExecuteEntryService executionService;

    @Test
    public void shouldSendPostRequestToExecuteEntry() throws Exception {
        EntryExecutionJson payload = createObject(EntryExecutionJson.class, ExecuteEntryTemplateLoader.VALID_EXECUTED_ENTRY_TEMPLATE);
        EntryJson resultJson = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        BDDMockito.given(this.executionService.executeEntry(MOCK_ENTRY_ID, payload)).willReturn(resultJson);
        String json = new ObjectMapper().writeValueAsString(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultJson));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/entries/1/execute").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

}
