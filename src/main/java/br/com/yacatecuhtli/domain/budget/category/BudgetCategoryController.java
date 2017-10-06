package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.controller.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget-categories")
public class BudgetCategoryController extends AbstractCrudController<BudgetCategoryJson, BudgetCategory, BudgetCategoryConverter, BudgetCategoryRepository, BudgetCategoryValidator, BudgetCategoryService> {

    @Autowired
    public BudgetCategoryController(BudgetCategoryService entityService) {
        super(entityService);
    }

}
