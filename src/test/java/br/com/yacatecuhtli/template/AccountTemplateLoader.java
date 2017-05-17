package br.com.yacatecuhtli.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import com.github.javafaker.Faker;

public class AccountTemplateLoader implements TemplateLoader {

    public static final String VALID_ACCOUNT_TEMPLATE = "valid";

    private static final Faker FAKER = new Faker();

    @Override
    public void load() {
        Fixture.of(Account.class).addTemplate(VALID_ACCOUNT_TEMPLATE, new Rule() {{
            add("name", uniqueRandom(FAKER.lorem().words(30).toArray()));
        }});

        Fixture.of(AccountJson.class).addTemplate(VALID_ACCOUNT_TEMPLATE, new Rule() {{
            add("name", uniqueRandom(FAKER.lorem().words(30).toArray()));
        }});
    }

}
