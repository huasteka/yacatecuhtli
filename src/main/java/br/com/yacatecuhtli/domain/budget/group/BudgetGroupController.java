package br.com.yacatecuhtli.domain.budget.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yacatecuhtli.core.controller.ControllerAdapter;

@RestController
@RequestMapping("/api/budget-groups")
public class BudgetGroupController extends ControllerAdapter<BudgetGroupJson> {

    @Autowired
    public BudgetGroupController(BudgetGroupService entityService) {
        super(entityService);
    }

}
