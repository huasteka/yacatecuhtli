package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.base.Range;
import br.com.six2six.fixturefactory.base.Sequence;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryJson;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

public class EntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_ENTRY_TEMPLATE = "valid";
    public static final String VALID_EXECUTED_ENTRY_TEMPLATE = "valid_executed";

    @Override
    public void load() {
        Sequence<BigDecimal> highValueSequence = () -> new BigDecimal(FAKER.number().randomDouble(2, 6000, 10000)).setScale(2, RoundingMode.HALF_EVEN);
        Sequence<BigDecimal> lowerValueSequence = () -> new BigDecimal(FAKER.number().randomDouble(2, 0, 4000)).setScale(2, RoundingMode.HALF_EVEN);

        Rule baseRule = new Rule();
        baseRule.add("code", baseRule.uniqueRandom(FAKER.lorem().words(10).toArray()));
        baseRule.add("issuedAt", null);
        baseRule.add("type", baseRule.uniqueRandom(EntryType.class));
        baseRule.add("grossValue", baseRule.sequence(highValueSequence));
        baseRule.add("netValue", baseRule.sequence(highValueSequence));
        baseRule.add("addition", baseRule.sequence(lowerValueSequence));
        baseRule.add("discount", baseRule.sequence(lowerValueSequence));
        baseRule.add("description", FAKER.lorem().sentence());

        Rule entityRule = new Rule();
        entityRule.add("account", entityRule.one(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        entityRule.add("paymentType", entityRule.one(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE));
        entityRule.add("category", entityRule.one(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE));

        Rule validRule = new Rule(baseRule, entityRule);
        validRule.add("executedAt", null);
        Fixture.of(Entry.class).addTemplate(VALID_ENTRY_TEMPLATE, validRule);

        Rule executedRule = new Rule(baseRule, entityRule);
        executedRule.add("executedAt", executedRule.sequence(() -> FAKER.date().past(1, TimeUnit.HOURS)));
        Fixture.of(Entry.class).addTemplate(VALID_EXECUTED_ENTRY_TEMPLATE, executedRule);

        Rule jsonRule = new Rule();
        jsonRule.add("account", jsonRule.one(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        jsonRule.add("paymentType", jsonRule.one(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE));
        jsonRule.add("category", jsonRule.one(BudgetCategoryJson.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE));
        jsonRule.add("executedAt", null);
        Fixture.of(EntryJson.class).addTemplate(VALID_ENTRY_TEMPLATE, new Rule(baseRule, jsonRule));
    }

}
