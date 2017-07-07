package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.entry.EntryConverter;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import br.com.yacatecuhtli.template.ScheduledEntryTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ScheduledEntryServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected EntryConverter entryConverter;

    @SpyBean
    protected ScheduledEntryConverter scheduledEntryConverter;

    @SpyBean
    protected ScheduledEntryService scheduledEntryService;

    @Test
    public void shouldScheduleDepositEntry() {
        doScheduleEntry(EntryType.DEPOSIT);
    }

    @Test
    public void shouldScheduleWithdrawEntry() {
        doScheduleEntry(EntryType.WITHDRAW);
    }

    private void doScheduleEntry(EntryType entryType) {
        Account account = createPersistedObject(Account.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentType paymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        EntryJson entryJson = createObject(EntryJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        entryJson.setAccount(account.toJson());
        entryJson.setPaymentType(paymentType.toJson());

        BudgetCategory category = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);
        ScheduledEntryJson scheduledEntryJson = createObject(ScheduledEntryJson.class, ScheduledEntryTemplateLoader.VALID_SCHEDULED_ENTRY_TEMPLATE);
        scheduledEntryJson.setEntry(entryJson);
        scheduledEntryJson.setCategory(category.toJson());

        ScheduledEntryJson savedScheduledEntry = scheduledEntryService.scheduleEntry(scheduledEntryJson, entryType);
        Assert.assertThat(savedScheduledEntry.getId(), Matchers.notNullValue());
        Assert.assertThat(savedScheduledEntry.getCategory(), Matchers.equalTo(scheduledEntryJson.getCategory()));
        Assert.assertThat(savedScheduledEntry.getEntry().getId(), Matchers.notNullValue());
        Assert.assertThat(savedScheduledEntry.getEntry().getType(), Matchers.equalTo(entryType));
        Assert.assertThat(savedScheduledEntry.getEntry().getAccount(), Matchers.equalTo(account.toJson()));
        Assert.assertThat(savedScheduledEntry.getEntry().getPaymentType(), Matchers.equalTo(paymentType.toJson()));
        Assert.assertThat(savedScheduledEntry.getExecuteAt(), Matchers.equalTo(scheduledEntryJson.getExecuteAt()));
    }

}
