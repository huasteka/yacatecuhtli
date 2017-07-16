package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupJson;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetCategoryServiceValidationTests extends AbstractBudgetCategoryServiceTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveBlankName() {
        BudgetGroup validBudgetGroup = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetCategoryJson payload = createObject(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        payload.setGroup(validBudgetGroup.toJson());
        payload.setName(null);
        budgetCategoryService.save(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveDuplicatedName() {
        BudgetCategory original = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        BudgetCategoryJson payload = BudgetCategoryJson.builder().name(original.getName()).build();
        budgetCategoryService.save(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateBudgetCategoryWithoutGroup() {
        BudgetCategoryJson payload = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE).toJson();
        payload.setGroup(null);
        budgetCategoryService.update(payload.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateBudgetCategoryWithInvalidGroup() {
        BudgetCategoryJson payload = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE).toJson();
        int budgetGroupId = payload.getGroup().getId();
        payload.setGroup(BudgetGroupJson.builder().id(budgetGroupId + 1).build());
        budgetCategoryService.update(payload.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedBudgetCategory() {
        BudgetCategory original = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        BudgetCategory duplicated = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        BudgetCategoryJson payload = duplicated.toJson();
        payload.setName(original.getName());
        budgetCategoryService.update(duplicated.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteIfBudgetCategoryNotExists() {
        budgetCategoryService.destroy(999);
    }

}
