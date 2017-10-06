package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.domain.account.balance.AbstractAccountBalanceServiceTests;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import br.com.yacatecuhtli.template.ExecuteEntryTemplateLoader;
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
public class ExecuteEntryServiceTests extends AbstractAccountBalanceServiceTests {

    @SpyBean
    protected ExecuteEntryValidator executeEntryValidator;

    @SpyBean
    protected ExecuteEntryService executeEntryService;

    @Test
    public void shouldExecuteEntry() {
        Entry entry = createPersistedObject(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);

        EntryExecutionJson json = createObject(EntryExecutionJson.class, ExecuteEntryTemplateLoader.VALID_EXECUTED_ENTRY_TEMPLATE);
        json.setBudgetCategoryId(budgetCategory.getId());

        Integer entryId = entry.getId();
        json.setEntryId(entryId);
        executeEntryService.executeEntry(json);

        Entry executed = getObject(Entry.class, entryId);
        Assert.assertThat(executed.getExecutedAt(), Matchers.notNullValue());
        Assert.assertThat(executed.getCategory().getId(), Matchers.equalTo(json.getBudgetCategoryId()));
        Assert.assertThat(executed.getAddition(), Matchers.equalTo(json.getAddition()));
        Assert.assertThat(executed.getDiscount(), Matchers.equalTo(json.getDiscount()));
        Assert.assertThat(executed.getNetValue(), Matchers.greaterThan(BigDecimal.ZERO));
    }

}
