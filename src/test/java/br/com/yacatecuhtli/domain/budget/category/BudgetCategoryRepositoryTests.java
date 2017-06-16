package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BudgetCategoryRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Test
    public void shouldFindByName() {
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        BudgetCategory savedBudgetCategory = this.budgetCategoryRepository.findByNameLikeIgnoreCase(budgetCategory.getName().toUpperCase());
        Assert.assertThat(savedBudgetCategory.getId(), Matchers.equalTo(budgetCategory.getId()));
    }

}
