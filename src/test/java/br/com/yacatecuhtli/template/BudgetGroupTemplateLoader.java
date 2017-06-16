package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupJson;

public class BudgetGroupTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_BUDGET_GROUP_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule rule = new Rule();
        rule.add("name", rule.uniqueRandom(FAKER.lorem().words(30).toArray()));
        Fixture.of(BudgetGroup.class).addTemplate(VALID_BUDGET_GROUP_TEMPLATE, rule);
        Fixture.of(BudgetGroupJson.class).addTemplate(VALID_BUDGET_GROUP_TEMPLATE, rule);
    }

}
