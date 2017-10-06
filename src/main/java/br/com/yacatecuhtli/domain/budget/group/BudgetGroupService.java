package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetGroupService extends AbstractCrudService<BudgetGroupJson, BudgetGroup, BudgetGroupConverter, BudgetGroupRepository, BudgetGroupValidator> {

    @Autowired
    public BudgetGroupService(BudgetGroupConverter jsonConverter, BudgetGroupRepository entityRepository, BudgetGroupValidator validator) {
        super(jsonConverter, entityRepository, validator);
    }

}
