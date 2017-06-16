package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.json.JsonConverter;
import org.springframework.stereotype.Component;

@Component
public class BudgetGroupConverter extends JsonConverter<BudgetGroupJson, BudgetGroup> {

    @Override
    public BudgetGroup convert(BudgetGroupJson source) {
        BudgetGroup budgetGroup = new BudgetGroup();
        update(source, budgetGroup);
        return budgetGroup;
    }

    @Override
    public void update(BudgetGroupJson source, BudgetGroup target) {
        target.setName(source.getName());
    }

}
