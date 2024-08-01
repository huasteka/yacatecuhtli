package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.template.ScheduledEntryTemplateLoader;
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
@WebMvcTest(value = {ScheduledEntryController.class}, secure = false)
public class ScheduledEntryControllerTests extends AbstractControllerSpec {

    @MockBean
    private ScheduledEntryService scheduledEntryService;

    @Test
    public void shouldSendPostRequestToScheduleDeposit() throws Exception {
        doSendPostRequestToSchedule("/api/schedules/deposit", EntryType.DEPOSIT);
    }

    @Test
    public void shouldSendPostRequestToScheduleWithdraw() throws Exception {
        doSendPostRequestToSchedule("/api/schedules/withdraw", EntryType.WITHDRAW);
    }

    private void doSendPostRequestToSchedule(String resource, EntryType type) throws Exception {
        ScheduledEntryJson payload = createObject(ScheduledEntryJson.class, ScheduledEntryTemplateLoader.VALID_SCHEDULED_ENTRY_TEMPLATE);
        BDDMockito.given(this.scheduledEntryService.scheduleEntry(payload, type)).willReturn(payload);
        String json = new ObjectMapper().writeValueAsString(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post(resource).contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

}
