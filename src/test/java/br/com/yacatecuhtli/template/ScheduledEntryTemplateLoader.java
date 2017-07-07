package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryJson;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.schedule.ScheduledEntry;
import br.com.yacatecuhtli.domain.entry.schedule.ScheduledEntryJson;

import java.util.concurrent.TimeUnit;

public class ScheduledEntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_SCHEDULED_ENTRY_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule baseRule = new Rule();
        baseRule.add("executeAt", baseRule.sequence(() -> FAKER.date().future(60, TimeUnit.DAYS)));

        Rule jsonRule = new Rule();
        jsonRule.add("entry", jsonRule.one(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        jsonRule.add("category", jsonRule.one(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE));
        Fixture.of(ScheduledEntryJson.class).addTemplate(VALID_SCHEDULED_ENTRY_TEMPLATE, new Rule(baseRule, jsonRule));

        Rule entityRule = new Rule();
        entityRule.add("entry", entityRule.one(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE));
        entityRule.add("category", entityRule.one(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE));
        Fixture.of(ScheduledEntry.class).addTemplate(VALID_SCHEDULED_ENTRY_TEMPLATE, new Rule(baseRule, entityRule));
    }

}
