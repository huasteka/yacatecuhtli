package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.entry.EntryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledEntryService extends AbstractService {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private EntryValidator entryValidator;

    @Autowired
    private ScheduledEntryRepository scheduledEntryRepository;

    @Autowired
    private ScheduledEntryConverter scheduledEntryConverter;

    @Transactional
    public ScheduledEntryJson scheduleEntry(ScheduledEntryJson scheduledEntry, EntryType type) {
        validate(scheduledEntry);
        scheduledEntry.getEntry().setType(type);
        return scheduledEntryRepository.save(scheduledEntryConverter.convert(scheduledEntry)).toJson();
    }

    private void validate(ScheduledEntryJson scheduledEntry) {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatCategoryIsNotNullAndExists(scheduledEntry, exception);
        ensureThatEntryIsValid(scheduledEntry, exception);
        ensureThatExecuteAtIsNotNull(scheduledEntry, exception);
        exception.throwException();
    }

    private void ensureThatCategoryIsNotNullAndExists(ScheduledEntryJson scheduledEntry, BusinessRuleException exception) {
        if (scheduledEntry.getCategory() == null || scheduledEntry.getCategory().getId() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_CATEGORY_IS_BLANK);
        } else {
            BudgetCategory budgetCategory = budgetCategoryRepository.findOne(scheduledEntry.getCategory().getId());
            if (budgetCategory == null) {
                exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_CATEGORY_UNAVAILABLE);
            }
        }
    }

    private void ensureThatEntryIsValid(ScheduledEntryJson scheduledEntry, BusinessRuleException exception) {
        if (scheduledEntry.getEntry() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_IS_BLANK);
        } else if (scheduledEntry.getEntry().getId() != null) {
            ensureThatEntryWasNotExecutedOrReversed(scheduledEntry, exception);
        } else {
            entryValidator.validateWithoutType(scheduledEntry.getEntry(), exception);
        }
    }

    private void ensureThatEntryWasNotExecutedOrReversed(ScheduledEntryJson scheduledEntry, BusinessRuleException exception) {
        Entry entry = entryRepository.getOne(scheduledEntry.getEntry().getId());
        if (entry.getExecutedAt() != null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_HAS_BEEN_EXECUTED);
        } else if (entry.getReversedAt() != null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_ENTRY_HAS_BEEN_REVERSED);
        }
    }

    private void ensureThatExecuteAtIsNotNull(ScheduledEntryJson scheduledEntry, BusinessRuleException exception) {
        if (scheduledEntry.getExecuteAt() == null) {
            exception.addMessage(ScheduledEntryMessageCode.SCHEDULED_ENTRY_EXECUTE_AT_IS_BLANK);
        }
    }

}
