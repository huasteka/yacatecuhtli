package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryJson;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupJson;

public class BudgetCategoryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_BUDGET_CATEGORY_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule entityRule = new Rule();
        entityRule.add("name", entityRule.uniqueRandom(FAKER.lorem().words(30).toArray()));
        entityRule.add("group", entityRule.one(BudgetGroup.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE));
        Fixture.of(BudgetCategory.class).addTemplate(VALID_BUDGET_CATEGORY_TEMPLATE, entityRule);

        Rule jsonRule = new Rule();
        jsonRule.add("name", jsonRule.uniqueRandom(FAKER.lorem().words(30).toArray()));
        jsonRule.add("group", jsonRule.one(BudgetGroupJson.class, BudgetGroupTemplateLoader.VALID_BUDGET_GROUP_TEMPLATE));
        Fixture.of(BudgetCategoryJson.class).addTemplate(VALID_BUDGET_CATEGORY_TEMPLATE, jsonRule);
    }

}
