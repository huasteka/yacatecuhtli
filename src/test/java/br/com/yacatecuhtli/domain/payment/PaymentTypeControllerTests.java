package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
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
@WebMvcTest(value = {PaymentTypeController.class}, secure = false)
public class PaymentTypeControllerTests extends AbstractControllerSpec {

    @MockBean
    private PaymentTypeService paymentTypeService;

    @Test
    public void shouldSendGetRequest() throws Exception {
        List<PaymentTypeJson> resultSet = createObjectList(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE, 5);
        JsonPagedResponse<PaymentTypeJson> pagedResult = new JsonPagedResponse<>(resultSet, new JsonResponseMetadata(10, 0, 1, 1L));
        BDDMockito.given(this.paymentTypeService.findAll(new PageRequest(0, 10))).willReturn(pagedResult);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultSet));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/payment-types")
                .param("currentPage", "0")
                .param("pageSize", "10")
                .accept(getContentType());
        this.getMvc().perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendGetWithPathParameterRequest() throws Exception {
        int paymentTypeId = 1;
        PaymentTypeJson result = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        BDDMockito.given(this.paymentTypeService.findOne(paymentTypeId)).willReturn(result);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/payment-types/{paymentTypeId}", paymentTypeId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendPostRequest() throws Exception {
        PaymentTypeJson payload = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        BDDMockito.given(this.paymentTypeService.save(payload)).willReturn(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/payment-types").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

    @Test
    public void shouldSendPutRequest() throws Exception {
        int paymentTypeId = 1;
        PaymentTypeJson payload = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        this.getMvc().perform(MockMvcRequestBuilders.put("/api/payment-types/{paymentTypeId}", paymentTypeId).contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldSendDeleteRequest() throws Exception {
        int paymentTypeId = 1;
        this.getMvc().perform(MockMvcRequestBuilders.delete("/api/payment-types/{paymentTypeId}", paymentTypeId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    
}
