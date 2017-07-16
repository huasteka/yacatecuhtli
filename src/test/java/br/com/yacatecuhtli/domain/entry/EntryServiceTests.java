package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryServiceTests extends AbstractEntryServiceTests {

    @Test
    public void shouldMakeDepositToAccount() {
        doPerformOperation(EntryType.DEPOSIT, entryService::deposit);
    }

    @Test
    public void shouldMakeWithdrawFromAccount() {
        doPerformOperation(EntryType.WITHDRAW, entryService::withdraw);
    }

    private void doPerformOperation(EntryType type, Function<EntryJson, EntryJson> functionTest) {
        AccountJson operationAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        PaymentTypeJson operationPaymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE).toJson();

        EntryJson payload = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setType(type);
        payload.setAccount(operationAccount);
        payload.setPaymentType(operationPaymentType);

        Assert.assertThat(payload.getId(), Matchers.nullValue());
        EntryJson savedEntry = functionTest.apply(payload);
        Assert.assertThat(savedEntry, Matchers.notNullValue());
        Assert.assertThat(savedEntry.getId(), Matchers.notNullValue());
        Assert.assertThat(savedEntry.getAccount().getId(), Matchers.equalTo(operationAccount.getId()));
        Assert.assertThat(savedEntry.getPaymentType().getId(), Matchers.equalTo(operationPaymentType.getId()));
    }

    @Test
    public void shouldFindPaginatedEntriesByAccount() {
        List<Entry> entryList = createPersistedObjectList(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE, 12);
        Account mainAccount = entryList.get(0).getAccount();
        Date issuedDate = entryList.get(0).getIssuedAt();
        entryList.forEach((entry) -> normalizeCommonValues(entry, mainAccount, issuedDate));

        JsonPagedResponse<EntryJson> result = this.entryService.findByAccount(mainAccount.getId(), new PageRequest(1, 10));
        Assert.assertThat(result, Matchers.notNullValue());
        Assert.assertThat(result.getMeta(), Matchers.notNullValue());
        Assert.assertThat(result.getMeta().getCurrentPage(), Matchers.equalTo(1));
        Assert.assertThat(result.getMeta().getPageSize(), Matchers.equalTo(10));
        Assert.assertThat(result.getMeta().getTotalPages(), Matchers.equalTo(2));
        Assert.assertThat(result.getMeta().getTotalItems(), Matchers.equalTo(12L));
        Assert.assertThat(result.getResult(), Matchers.notNullValue());
        Assert.assertThat(result.getResult().size(), Matchers.equalTo(2));
        Assert.assertThat(result.getResult().get(0), Matchers.equalTo(entryList.get(10).toJson()));
        Assert.assertThat(result.getResult().get(1), Matchers.equalTo(entryList.get(11).toJson()));
    }

    private void normalizeCommonValues(Entry entry, Account account, Date issuedDate) {
        entry.setAccount(account);
        entry.setIssuedAt(DateUtils.addDays(issuedDate, 1));
        persist(entry);
    }

}
