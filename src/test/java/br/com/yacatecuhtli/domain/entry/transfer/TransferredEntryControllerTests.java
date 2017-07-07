package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.template.TransferredEntryTemplateLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {TransferredEntryController.class}, secure = false)
public class TransferredEntryControllerTests extends AbstractControllerSpec {

    @MockBean
    private TransferredEntryService transferredEntryService;

    @Test
    public void shouldSendPostRequestToTransfer() throws Exception {
        TransferredEntryJson transferredEntryJson = createObject(TransferredEntryJson.class, TransferredEntryTemplateLoader.VALID_TRANSFERRED_ENTRY_TEMPLATE);
        BigDecimal amount = transferredEntryJson.getSource().getGrossValue();
        transferredEntryJson.getTarget().setGrossValue(amount);
        transferredEntryJson.getTarget().setType(EntryType.getReverseType(transferredEntryJson.getSource().getType()));
        AccountTransferJson payload = new AccountTransferJson(amount, 1, 2, 1);
        String json = new ObjectMapper().writeValueAsString(payload);
        BDDMockito.given(transferredEntryService.transfer(payload)).willReturn(transferredEntryJson);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(transferredEntryJson));
        getMvc().perform(MockMvcRequestBuilders.post("/api/transfers").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

}
