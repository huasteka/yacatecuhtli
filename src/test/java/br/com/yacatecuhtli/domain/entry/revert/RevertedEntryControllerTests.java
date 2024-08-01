package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {ReversedEntryController.class}, secure = false)
public class RevertedEntryControllerTests extends AbstractControllerSpec {

    private static final Integer MOCK_ENTRY_ID = 1;

    @MockBean
    private ReversedEntryService reversedEntryService;

    @Test
    public void shouldSendDeleteRequestToReverse() throws Exception {
        this.getMvc()
                .perform(MockMvcRequestBuilders.delete("/api/entries/{entryId}/reverse", MOCK_ENTRY_ID).contentType(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
