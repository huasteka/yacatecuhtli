package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractBudgetGroupServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected BudgetGroupConverter budgetGroupConverter;

    @SpyBean
    protected BudgetGroupService budgetGroupService;

}
