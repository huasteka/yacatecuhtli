package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetGroupServiceValidationTests extends AbstractBudgetGroupServiceTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveBlankName() {
        BudgetGroupJson invalid = createObject(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        invalid.setName(null);
        budgetGroupService.save(invalid);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveDuplicatedName() {
        BudgetGroup original = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetGroupJson payload = BudgetGroupJson.builder().name(original.getName()).build();
        budgetGroupService.save(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedBudgetGroup() {
        BudgetGroup original = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetGroup duplicated = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetGroupJson payload = duplicated.toJson();
        payload.setName(original.getName());
        budgetGroupService.update(duplicated.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteIfBudgetGroupNosExists() {
        budgetGroupService.destroy(999);
    }

}
