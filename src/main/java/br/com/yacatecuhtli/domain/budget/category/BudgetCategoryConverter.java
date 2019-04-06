package br.com.yacatecuhtli.domain.budget.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupRepository;

@Component
public class BudgetCategoryConverter extends JsonConverter<BudgetCategoryJson, BudgetCategory> {

    private BudgetGroupRepository budgetGroupRepository;

    @Autowired
    public BudgetCategoryConverter(BudgetGroupRepository budgetGroupRepository) {
		this.budgetGroupRepository = budgetGroupRepository;
	}

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
