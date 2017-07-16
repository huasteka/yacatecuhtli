package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountBalanceServiceTests extends AbstractAccountBalanceServiceTests {

    @Test
    public void shouldAccumulateValuesIntoAccount() {
        Account account = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE);
        PaymentType paymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        List<Entry> entries = createObjectList(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE, 10);
        BigDecimal balance = BigDecimal.ZERO;
        for (Entry entry : entries) {
            entry.setAccount(account);
            entry.setPaymentType(paymentType);
            balance = balance.add(EntryType.DEPOSIT.equals(entry.getType()) ? entry.getTotal() : entry.getTotal().negate());
        }
        this.persist(entries);
        entries.forEach(accountBalanceService::performOperation);

        DateTime dateTime = new DateTime(SystemTime.INSTANCE.now());
        AccountBalance accountBalance = accountBalanceService.findByAccount(account, dateTime.getYear(), dateTime.getMonthOfYear());
        Assert.assertThat(accountBalance.getBalance(), Matchers.equalTo(balance));
    }

}
