package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetCategoryService extends AbstractCrudService<BudgetCategoryJson, BudgetCategory, BudgetCategoryConverter, BudgetCategoryRepository, BudgetCategoryValidator> {

    @Autowired
    public BudgetCategoryService(BudgetCategoryConverter jsonConverter, BudgetCategoryRepository entityRepository, BudgetCategoryValidator validator) {
        super(jsonConverter, entityRepository, validator);
    }

}
