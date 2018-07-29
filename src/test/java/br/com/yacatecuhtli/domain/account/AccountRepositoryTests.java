package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldFindByCode() {
        Account account = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Account savedAccount = this.accountRepository.findByCodeLikeIgnoreCase(account.getCode().toUpperCase());
        Assert.assertThat(savedAccount.getId(), Matchers.equalTo(account.getId()));
    }

    @Test
    public void shouldFindByName() {
        Account account = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Account savedAccount = this.accountRepository.findByNameLikeIgnoreCase(account.getName().toUpperCase());
        Assert.assertThat(savedAccount.getId(), Matchers.equalTo(account.getId()));
    }

}
