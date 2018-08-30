package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.template.BudgetCategoryTemplateLoader;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExecuteEntryValidatorTests extends AbstractRepositorySpec {

    @SpyBean
    protected ExecuteEntryValidator executeEntryValidator;

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAcceptEntryExecutionWithoutEntry() {
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);

        EntryExecutionJson payload = createObject(EntryExecutionJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setBudgetCategoryId(budgetCategory.getId());
        executeEntryValidator.validate(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAcceptEntryExecutionWithNonExistentEntry() {
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);

        EntryExecutionJson payload = createObject(EntryExecutionJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setEntryId(999);
        payload.setBudgetCategoryId(budgetCategory.getId());
        executeEntryValidator.validate(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAcceptEntryExecutionWithoutBudgetCategory() {
        Entry entry = createPersistedObject(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);

        EntryExecutionJson payload = createObject(EntryExecutionJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setEntryId(entry.getId());
        executeEntryValidator.validate(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAcceptEntryExecutionWithNonExistentBudgetCategory() {
        Entry entry = createPersistedObject(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);

        EntryExecutionJson payload = createObject(EntryExecutionJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setEntryId(entry.getId());
        payload.setBudgetCategoryId(999);
        executeEntryValidator.validate(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAcceptEntryExecutionWithExecutedEntry() {
        Entry entry = createPersistedObject(Entry.class, EntryTemplateLoader.VALID_EXECUTED_ENTRY_TEMPLATE);
        BudgetCategory budgetCategory = createPersistedObject(BudgetCategory.class, BudgetCategoryTemplateLoader.VALID_BUDGET_CATEGORY_TEMPLATE);

        EntryExecutionJson payload = createObject(EntryExecutionJson.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        payload.setEntryId(entry.getId());
        payload.setBudgetCategoryId(budgetCategory.getId());
        executeEntryValidator.validate(payload);
    }

}
