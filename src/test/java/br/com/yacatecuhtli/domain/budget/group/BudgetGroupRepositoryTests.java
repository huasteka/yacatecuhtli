package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.template.BudgetGroupTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetGroupRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Test
    public void shouldFindByName() {
        BudgetGroup budgetGroup = createPersistedObject(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE);
        BudgetGroup savedBudgetGroup = this.budgetGroupRepository.findByNameLikeIgnoreCase(budgetGroup.getName().toUpperCase());
        Assert.assertThat(savedBudgetGroup.getId(), Matchers.equalTo(budgetGroup.getId()));
    }

}
