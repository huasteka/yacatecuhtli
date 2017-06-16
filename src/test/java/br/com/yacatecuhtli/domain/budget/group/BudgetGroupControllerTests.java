package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
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
@WebMvcTest(value = {BudgetGroupController.class}, secure = false)
public class BudgetGroupControllerTests extends AbstractControllerSpec {

    @MockBean
    private BudgetGroupService budgetGroupService;

    @Test
    public void shouldSendGetRequest() throws Exception {
        List<BudgetGroupJson> resultSet = createObjectList(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE, 5);
        JsonPagedResponse<BudgetGroupJson> pagedResult = new JsonPagedResponse<>(resultSet, new JsonResponseMetadata(10, 0, 1, 1L));
        BDDMockito.given(this.budgetGroupService.findAll(new PageRequest(0, 10))).willReturn(pagedResult);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultSet));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/budget-groups")
                .param("currentPage", "0")
                .param("pageSize", "10")
                .accept(getContentType());
        this.getMvc().perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendGetWithPathParameterRequest() throws Exception {
        int budgetGroupId = 1;
        BudgetGroupJson result = createObject(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BDDMockito.given(this.budgetGroupService.findOne(budgetGroupId)).willReturn(result);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/budget-groups/{budgetGroupId}", budgetGroupId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendPostRequest() throws Exception {
        BudgetGroupJson payload = createObject(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        BDDMockito.given(this.budgetGroupService.save(payload)).willReturn(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/budget-groups").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

    @Test
    public void shouldSendPutRequest() throws Exception {
        int budgetGroupId = 1;
        BudgetGroupJson payload = createObject(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        this.getMvc().perform(MockMvcRequestBuilders.put("/api/budget-groups/{budgetGroupId}", budgetGroupId).contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldSendDeleteRequest() throws Exception {
        int budgetGroupId = 1;
        this.getMvc().perform(MockMvcRequestBuilders.delete("/api/budget-groups/{budgetGroupId}", budgetGroupId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
