package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.AbstractRepositoryTests;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import com.github.javafaker.Faker;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTests extends AbstractRepositoryTests {

    @SpyBean
    private AccountConverter accountConverter;

    @SpyBean
    private AccountService accountService;

    @Test
    public void shouldListAccounts() {
        int accountQuantity = 10;
        List<Account> accountList = createPersistedObjectList(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE, accountQuantity);
        List<AccountJson> resultSet = accountService.findAll();
        MatcherAssert.assertThat(resultSet.size(), Matchers.equalTo(accountQuantity));
        for (Account account : accountList) {
            resultSet.stream()
                    .filter(result -> account.getId().compareTo(result.getId()) == 0)
                    .forEach(result -> MatcherAssert.assertThat(result, Matchers.samePropertyValuesAs(account.toJson())));
        }
    }

    @Test
    public void shouldFindAccountById() {
        Account savedAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        int savedAccountId = savedAccount.getId();
        AccountJson result = accountService.findOne(savedAccountId);
        MatcherAssert.assertThat(result.getId(), Matchers.equalTo(savedAccountId));
        MatcherAssert.assertThat(result, Matchers.samePropertyValuesAs(savedAccount.toJson()));
    }

    @Test
    public void shouldSaveAccount() {
        AccountJson payload = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        MatcherAssert.assertThat(payload.getId(), Matchers.nullValue());
        AccountJson savedAccount = accountService.save(payload);
        MatcherAssert.assertThat(savedAccount, Matchers.notNullValue());
        MatcherAssert.assertThat(savedAccount.getId(), Matchers.notNullValue());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveDuplicatedName() {
        Account original = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        AccountJson payload = AccountJson.builder().name(original.getName()).build();
        accountService.save(payload);
    }

    @Test
    public void shouldUpdateAccount() {
        AccountJson payload = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        String oldName = payload.getName();
        payload.setName(new Faker().lorem().characters(15));
        accountService.update(payload.getId(), payload);
        Account updatedAccount = getObject(Account.class, payload.getId());
        MatcherAssert.assertThat(oldName, Matchers.not(Matchers.equalToIgnoringCase(updatedAccount.getName())));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedAccount() {
        Account original = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Account duplicated = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        AccountJson payload = duplicated.toJson();
        payload.setName(original.getName());
        accountService.update(duplicated.getId(), payload);
    }

}
