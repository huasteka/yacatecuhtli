package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.AbstractRepositoryTests;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests extends AbstractRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindByName() {
        Account account = createObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        this.entityManager.persist(account);
        Account savedAccount = this.accountRepository.findByNameLikeIgnoreCase(account.getName().toUpperCase());
        MatcherAssert.assertThat(savedAccount.getId(), Matchers.equalTo(account.getId()));
    }

}
