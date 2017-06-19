package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryServiceTests extends AbstractRepositorySpec {

    @SpyBean
    private EntryConverter entryConverter;

    @SpyBean
    private EntryService entryService;

    @Test
    public void shouldMakeDepositToAccount() {
        AccountJson depositAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        PaymentTypeJson depositPaymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE).toJson();

        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setType(EntryType.DEPOSIT);
        payload.setAccount(depositAccount);
        payload.setPaymentType(depositPaymentType);

        Assert.assertThat(payload.getId(), Matchers.nullValue());
        EntryJson savedEntry = this.entryService.deposit(payload);
        Assert.assertThat(savedEntry, Matchers.notNullValue());
        Assert.assertThat(savedEntry.getId(), Matchers.notNullValue());
        Assert.assertThat(savedEntry.getAccount().getId(), Matchers.equalTo(depositAccount.getId()));
        Assert.assertThat(savedEntry.getPaymentType().getId(), Matchers.equalTo(depositPaymentType.getId()));
    }

    @Test
    public void shouldMakeWithdrawFromAccount() {
        AccountJson withdrawAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        PaymentTypeJson withdrawPaymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE).toJson();

        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setType(EntryType.WITHDRAW);
        payload.setAccount(withdrawAccount);
        payload.setPaymentType(withdrawPaymentType);

        Assert.assertThat(payload.getId(), Matchers.nullValue());
        EntryJson savedEntry = this.entryService.withdraw(payload);
        Assert.assertThat(savedEntry, Matchers.notNullValue());
        Assert.assertThat(savedEntry.getId(), Matchers.notNullValue());
        Assert.assertThat(savedEntry.getAccount().getId(), Matchers.equalTo(withdrawAccount.getId()));
        Assert.assertThat(savedEntry.getPaymentType().getId(), Matchers.equalTo(withdrawPaymentType.getId()));
    }

}
