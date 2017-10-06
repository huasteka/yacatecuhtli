package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.controller.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget-groups")
public class BudgetGroupController extends AbstractCrudController<BudgetGroupJson, BudgetGroup, BudgetGroupConverter, BudgetGroupRepository, BudgetGroupValidator, BudgetGroupService> {

    @Autowired
    public BudgetGroupController(BudgetGroupService entityService) {
        super(entityService);
    }

}
