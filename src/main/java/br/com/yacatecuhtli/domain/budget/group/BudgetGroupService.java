package br.com.yacatecuhtli.domain.budget.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yacatecuhtli.core.service.ServiceAdapter;

@Service
public class BudgetGroupService extends ServiceAdapter<BudgetGroupJson, BudgetGroup> {

    @Autowired
    public BudgetGroupService(BudgetGroupValidator validator, BudgetGroupConverter converter, BudgetGroupRepository repository) {
        super(validator, converter, repository);
    }

}
