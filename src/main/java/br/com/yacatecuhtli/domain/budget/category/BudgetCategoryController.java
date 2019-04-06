package br.com.yacatecuhtli.domain.budget.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yacatecuhtli.core.controller.ControllerAdapter;

@RestController
@RequestMapping("/api/budget-categories")
public class BudgetCategoryController extends ControllerAdapter<BudgetCategoryJson> {

    @Autowired
    public BudgetCategoryController(BudgetCategoryService entityService) {
        super(entityService);
    }

}
