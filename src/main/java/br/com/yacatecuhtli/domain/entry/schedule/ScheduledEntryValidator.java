package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.VoidValidator;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.EntryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEntryValidator extends VoidValidator<ScheduledEntryJson> {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private EntryValidator entryValidator;

    @Override
    public void executeValidation(ScheduledEntryJson scheduledEntry) throws BusinessRuleException {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatCategoryIsNotNullAndExists(exception, scheduledEntry);
        ensureThatEntryIsValid(exception, scheduledEntry);
        ensureThatExecuteAtIsNotNull(exception, scheduledEntry);
        exception.throwException();
    }

    private void ensureThatCategoryIsNotNullAndExists(BusinessRuleException exception, ScheduledEntryJson scheduledEntry) {
        if (scheduledEntry.getCategory() == null || scheduledEntry.getCategory().getId() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_CATEGORY_IS_BLANK);
        } else {
            BudgetCategory budgetCategory = budgetCategoryRepository.findOne(scheduledEntry.getCategory().getId());
            if (budgetCategory == null) {
                exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_CATEGORY_UNAVAILABLE);
            }
        }
    }

    private void ensureThatEntryIsValid(BusinessRuleException exception, ScheduledEntryJson scheduledEntry) {
        if (scheduledEntry.getEntry() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_IS_BLANK);
        } else if (scheduledEntry.getEntry().getId() != null) {
            ensureThatEntryWasNotExecutedOrReversed(exception, scheduledEntry);
        } else {
            entryValidator.executeValidation(exception, scheduledEntry.getEntry());
        }
    }

    private void ensureThatEntryWasNotExecutedOrReversed(BusinessRuleException exception, ScheduledEntryJson scheduledEntry) {
        Entry entry = entryRepository.getOne(scheduledEntry.getEntry().getId());
        if (entry.getExecutedAt() != null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_HAS_BEEN_EXECUTED);
        } else if (entry.getReversedAt() != null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_HAS_BEEN_REVERSED);
        }
    }

    private void ensureThatExecuteAtIsNotNull(BusinessRuleException exception, ScheduledEntryJson scheduledEntry) {
        if (scheduledEntry.getExecuteAt() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_EXECUTE_AT_IS_BLANK);
        }
    }

}
