package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractBudgetCategoryServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected BudgetCategoryValidator budgetCategoryValidator;

    @SpyBean
    protected BudgetCategoryConverter budgetCategoryConverter;

    @SpyBean
    protected BudgetCategoryService budgetCategoryService;

}
