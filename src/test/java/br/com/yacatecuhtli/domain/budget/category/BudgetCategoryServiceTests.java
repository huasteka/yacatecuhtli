package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetCategoryServiceTests extends AbstractBudgetCategoryServiceTests {

    @Test
    public void shouldListBudgetCategorys() {
        int budgetCategoryQuantity = 10;
        List<BudgetCategory> budgetCategoryList = createPersistedObjectList(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE, budgetCategoryQuantity);
        List<BudgetCategoryJson> resultSet = budgetCategoryService.findAll(new PageRequest(0, 10)).getResult();
        Assert.assertThat(resultSet.size(), Matchers.equalTo(budgetCategoryQuantity));
        for (BudgetCategory budgetCategory : budgetCategoryList) {
            resultSet.stream()
                    .filter(result -> budgetCategory.getId().compareTo(result.getId()) == 0)
                    .forEach(result -> Assert.assertThat(result, Matchers.samePropertyValuesAs(budgetCategory.toJson())));
        }
    }

    @Test
    public void shouldFindBudgetCategoryById() {
        BudgetCategory savedBudgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        int savedBudgetCategoryId = savedBudgetCategory.getId();
        BudgetCategoryJson result = budgetCategoryService.findOne(savedBudgetCategoryId);
        Assert.assertThat(result.getId(), Matchers.equalTo(savedBudgetCategoryId));
        Assert.assertThat(result, Matchers.samePropertyValuesAs(savedBudgetCategory.toJson()));
    }

    @Test
    public void shouldSaveBudgetCategory() {
        BudgetGroup validBudgetGroup = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetCategoryJson payload = createObject(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        payload.setGroup(validBudgetGroup.toJson());
        Assert.assertThat(payload.getId(), Matchers.nullValue());
        BudgetCategoryJson savedBudgetCategory = budgetCategoryService.save(payload);
        Assert.assertThat(savedBudgetCategory, Matchers.notNullValue());
        Assert.assertThat(savedBudgetCategory.getId(), Matchers.notNullValue());
    }

    @Test
    public void shouldUpdateBudgetCategory() {
        BudgetCategoryJson payload = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE).toJson();
        String oldName = payload.getName();
        payload.setName(new Faker().lorem().characters(15));
        budgetCategoryService.update(payload.getId(), payload);
        BudgetCategory updatedBudgetCategory = getObject(BudgetCategory.class, payload.getId());
        Assert.assertThat(oldName, Matchers.not(Matchers.equalToIgnoringCase(updatedBudgetCategory.getName())));
    }

    @Test
    public void shouldDeleteBudgetCategory() {
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        Integer budgetCategoryId = budgetCategory.getId();
        Integer budgetGroupId = budgetCategory.getGroup().getId();

        budgetCategoryService.destroy(budgetCategoryId);
        BudgetCategory deleted = getObject(BudgetCategory.class, budgetCategoryId);
        BudgetGroup exists = getObject(BudgetGroup.class, budgetGroupId);
        Assert.assertThat(deleted, Matchers.nullValue());
        Assert.assertThat(exists, Matchers.notNullValue());
    }

}
