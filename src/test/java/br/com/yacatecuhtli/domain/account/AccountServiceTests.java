package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.template.AccountTemplateLoader;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTests extends AbstractAccountServiceTests {

    @Test
    public void shouldListAccounts() {
        int accountQuantity = 10;
        List<Account> accountList = createPersistedObjectList(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE, accountQuantity);
        List<AccountJson> resultSet = accountService.findAll(new PageRequest(0, 10)).getResult();
        Assert.assertThat(resultSet.size(), Matchers.equalTo(accountQuantity));
        for (Account account : accountList) {
            resultSet.stream()
                    .filter(result -> account.getId().compareTo(result.getId()) == 0)
                    .forEach(result -> Assert.assertThat(result, Matchers.samePropertyValuesAs(account.toJson())));
        }
    }

    @Test
    public void shouldFindAccountById() {
        Account savedAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        int savedAccountId = savedAccount.getId();
        AccountJson result = accountService.findOne(savedAccountId);
        Assert.assertThat(result.getId(), Matchers.equalTo(savedAccountId));
        Assert.assertThat(result, Matchers.samePropertyValuesAs(savedAccount.toJson()));
    }

    @Test
    public void shouldSaveAccount() {
        AccountJson payload = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Assert.assertThat(payload.getId(), Matchers.nullValue());
        AccountJson savedAccount = accountService.save(payload);
        Assert.assertThat(savedAccount, Matchers.notNullValue());
        Assert.assertThat(savedAccount.getId(), Matchers.notNullValue());
    }

    @Test
    public void shouldUpdateAccount() {
        AccountJson payload = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        String oldName = payload.getName();
        payload.setName(new Faker().lorem().characters(15));
        accountService.update(payload.getId(), payload);
        Account updatedAccount = getObject(Account.class, payload.getId());
        Assert.assertThat(oldName, Matchers.not(Matchers.equalToIgnoringCase(updatedAccount.getName())));
    }

    @Test
    public void shouldDeleteAccount() {
        Integer accountId = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).getId();
        accountService.destroy(accountId);
        Account deleted = getObject(Account.class, accountId);
        Assert.assertThat(deleted, Matchers.nullValue());
    }

}
