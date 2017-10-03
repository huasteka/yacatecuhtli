package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.yacatecuhtli.core.AbstractTemplateLoader;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTerms;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsJson;

import java.math.BigDecimal;

public class PaymentTypeTemplateLoader extends AbstractTemplateLoader {

    public static final String VALID_PAYMENT_TYPE_TEMPLATE = "valid";

    @Override
    public void load() {
        Rule paymentTermsRule = new Rule();
        paymentTermsRule.add("stagedPayment", FAKER.bool().bool());
        paymentTermsRule.add("tax", new BigDecimal(FAKER.number().randomDouble(2, 0, 20)));
        paymentTermsRule.add("installmentQuantity", FAKER.number().numberBetween(1, 10));
        paymentTermsRule.add("firstInstallmentTerm", FAKER.number().numberBetween(1, 30));
        paymentTermsRule.add("installmentTerm", FAKER.number().numberBetween(1, 30));

        Fixture.of(PaymentTerms.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, paymentTermsRule);
        Fixture.of(PaymentTermsJson.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, paymentTermsRule);

        Rule entityRule = new Rule();
        entityRule.add("name", entityRule.uniqueRandom(FAKER.lorem().words(30).toArray()));
        entityRule.add("terms", entityRule.one(PaymentTerms.class, VALID_PAYMENT_TYPE_TEMPLATE));
        entityRule.add("paymentAccount", entityRule.one(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        Fixture.of(PaymentType.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, entityRule);

        Rule jsonRule = new Rule();
        jsonRule.add("name", jsonRule.uniqueRandom(FAKER.lorem().words(30).toArray()));
        jsonRule.add("terms", jsonRule.one(PaymentTermsJson.class, VALID_PAYMENT_TYPE_TEMPLATE));
        jsonRule.add("paymentAccount", jsonRule.one(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        Fixture.of(PaymentTypeJson.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, jsonRule);
    }

}
