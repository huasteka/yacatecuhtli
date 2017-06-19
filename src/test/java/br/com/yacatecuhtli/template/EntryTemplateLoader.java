package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;

import java.math.BigDecimal;

public class EntryTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_ENTRY_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule baseRule = new Rule();
        baseRule.add("issuedAt", null);
        baseRule.add("executedAt", null);
        baseRule.add("type", baseRule.uniqueRandom(EntryType.class));
        baseRule.add("grossValue", new BigDecimal(FAKER.number().randomDouble(2, 0L, 1000000L)));
        baseRule.add("netValue", new BigDecimal(FAKER.number().randomDouble(2, 0L, 1000000L)));
        baseRule.add("addition", new BigDecimal(FAKER.number().randomDouble(2, 0L, 1000L)));
        baseRule.add("discount", new BigDecimal(FAKER.number().randomDouble(2, 0L, 1000L)));
        baseRule.add("description", FAKER.lorem().sentence());

        Rule entityRule = new Rule();
        entityRule.add("account", entityRule.one(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        entityRule.add("paymentType", entityRule.one(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE));
        Fixture.of(Entry.class).addTemplate(VALID_ENTRY_TEMPLATE, new Rule(baseRule, entityRule));

        Rule jsonRule = new Rule();
        jsonRule.add("account", jsonRule.one(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        jsonRule.add("paymentType", jsonRule.one(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE));
        Fixture.of(EntryJson.class).addTemplate(VALID_ENTRY_TEMPLATE, new Rule(baseRule, jsonRule));
    }

}
