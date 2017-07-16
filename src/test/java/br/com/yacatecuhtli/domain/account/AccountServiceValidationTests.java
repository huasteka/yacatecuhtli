package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceValidationTests extends AbstractAccountServiceTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveBlankName() {
        AccountJson account = createObject(AccountJson.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        account.setName(null);
        accountService.save(account);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveDuplicatedName() {
        Account original = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        AccountJson payload = AccountJson.builder().name(original.getName()).build();
        accountService.save(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedAccount() {
        Account original = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Account duplicated = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        AccountJson payload = duplicated.toJson();
        payload.setName(original.getName());
        accountService.update(duplicated.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteIfAccountThatNotExists() {
        accountService.destroy(999);
    }

}
