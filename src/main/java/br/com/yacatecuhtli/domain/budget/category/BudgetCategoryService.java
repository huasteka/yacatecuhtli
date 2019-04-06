package br.com.yacatecuhtli.domain.budget.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yacatecuhtli.core.service.ServiceAdapter;

@Service
public class BudgetCategoryService extends ServiceAdapter<BudgetCategoryJson, BudgetCategory> {

    @Autowired
    public BudgetCategoryService(BudgetCategoryValidator validator, BudgetCategoryConverter converter, BudgetCategoryRepository repository) {
        super(validator, converter, repository);
    }

}
