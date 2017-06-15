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
        Rule paymentTermsRule = new Rule() {{
            add("stagedPayment", FAKER.bool().bool());
            add("tax", new BigDecimal(FAKER.number().randomDouble(2, 0, 100)));
            add("installmentQuantity", FAKER.number().numberBetween(1, 10));
            add("firstInstallmentTerm", FAKER.number().numberBetween(1, 30));
            add("installmentTerm", FAKER.number().numberBetween(1, 30));
        }};

        Fixture.of(PaymentTerms.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, paymentTermsRule);
        Fixture.of(PaymentTermsJson.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, paymentTermsRule);

        Fixture.of(PaymentType.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, new Rule() {{
            add("name", uniqueRandom(FAKER.lorem().words(30).toArray()));
            add("terms", one(PaymentTerms.class, VALID_PAYMENT_TYPE_TEMPLATE));
            add("paymentAccount", one(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        }});

        Fixture.of(PaymentTypeJson.class).addTemplate(VALID_PAYMENT_TYPE_TEMPLATE, new Rule() {{
            add("name", uniqueRandom(FAKER.lorem().words(30).toArray()));
            add("terms", one(PaymentTermsJson.class, VALID_PAYMENT_TYPE_TEMPLATE));
            add("paymentAccount", one(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE));
        }});
    }

}
