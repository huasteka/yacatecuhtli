package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BudgetCategoryConverter extends JsonConverter<BudgetCategoryJson, BudgetCategory> {

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Override
    public BudgetCategory convert(BudgetCategoryJson source) {
        BudgetCategory budgetCategory = new BudgetCategory();
        update(source, budgetCategory);
        return budgetCategory;
    }

    @Override
    public void update(BudgetCategoryJson source, BudgetCategory target) {
        target.setName(source.getName());
        updateRelationship(budgetGroupRepository, source.getGroup(), target::setGroup);
    }

}
