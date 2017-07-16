package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.balance.AbstractAccountBalanceServiceTests;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransferredEntryServiceTests extends AbstractAccountBalanceServiceTests {

    @SpyBean
    protected AccountTransferConverter accountTransferConverter;

    @SpyBean
    protected TransferredEntryService transferredEntryService;

    @Test
    public void shouldTransferEntryFromAccount() {
        BigDecimal transferredValue = BigDecimal.TEN.multiply(BigDecimal.TEN);

        PaymentType paymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        Account sourceAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        Account targetAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);

        AccountTransferJson payload = new AccountTransferJson();
        payload.setPaymentTypeId(paymentType.getId());
        payload.setSourceAccountId(sourceAccount.getId());
        payload.setTargetAccountId(targetAccount.getId());
        payload.setAmount(transferredValue);

        TransferredEntryJson result = transferredEntryService.transfer(payload);
        Assert.assertThat(result.getId(), Matchers.notNullValue());

        Assert.assertThat(result.getSource(), Matchers.notNullValue());
        Assert.assertThat(result.getSource().getType(), Matchers.equalTo(EntryType.WITHDRAW));
        Assert.assertThat(result.getSource().getGrossValue(), Matchers.equalTo(transferredValue));
        Assert.assertThat(result.getSource().getNetValue(), Matchers.equalTo(transferredValue));

        Assert.assertThat(result.getTarget(), Matchers.notNullValue());
        Assert.assertThat(result.getTarget().getType(), Matchers.equalTo(EntryType.DEPOSIT));
        Assert.assertThat(result.getTarget().getGrossValue(), Matchers.equalTo(transferredValue));
        Assert.assertThat(result.getTarget().getNetValue(), Matchers.equalTo(transferredValue));
    }

}
