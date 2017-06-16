package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.AbstractControllerSpec;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonResponseFactory;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
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
@WebMvcTest(value = {BudgetCategoryController.class}, secure = false)
public class BudgetCategoryControllerTests extends AbstractControllerSpec {

    @MockBean
    private BudgetCategoryService budgetCategoryService;

    @Test
    public void shouldSendGetRequest() throws Exception {
        List<BudgetCategoryJson> resultSet = createObjectList(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE, 5);
        JsonPagedResponse<BudgetCategoryJson> pagedResult = new JsonPagedResponse<>(resultSet, new JsonResponseMetadata(10, 0, 1, 1L));
        BDDMockito.given(this.budgetCategoryService.findAll(new PageRequest(0, 10))).willReturn(pagedResult);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(resultSet));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/budget-categories")
                .param("currentPage", "0")
                .param("pageSize", "10")
                .accept(getContentType());
        this.getMvc().perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendGetWithPathParameterRequest() throws Exception {
        int budgetCategoryId = 1;
        BudgetCategoryJson result = createObject(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        BDDMockito.given(this.budgetCategoryService.findOne(budgetCategoryId)).willReturn(result);
        String json = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(result));
        this.getMvc().perform(MockMvcRequestBuilders.get("/api/budget-categories/{budgetCategoryId}", budgetCategoryId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    @Test
    public void shouldSendPostRequest() throws Exception {
        BudgetCategoryJson payload = createObject(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        BDDMockito.given(this.budgetCategoryService.save(payload)).willReturn(payload);
        String result = new ObjectMapper().writeValueAsString(JsonResponseFactory.create(payload));
        this.getMvc().perform(MockMvcRequestBuilders.post("/api/budget-categories").contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(result));
    }

    @Test
    public void shouldSendPutRequest() throws Exception {
        int budgetCategoryId = 1;
        BudgetCategoryJson payload = createObject(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        String json = new ObjectMapper().writeValueAsString(payload);
        this.getMvc().perform(MockMvcRequestBuilders.put("/api/budget-categories/{budgetCategoryId}", budgetCategoryId).contentType(getContentType()).content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldSendDeleteRequest() throws Exception {
        int budgetCategoryId = 1;
        this.getMvc().perform(MockMvcRequestBuilders.delete("/api/budget-categories/{budgetCategoryId}", budgetCategoryId).accept(getContentType()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
