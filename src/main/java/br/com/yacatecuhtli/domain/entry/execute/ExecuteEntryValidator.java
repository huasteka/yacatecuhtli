package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import br.com.yacatecuhtli.core.validator.Validator;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.calculator.CalculatedEntry;
import br.com.yacatecuhtli.domain.entry.calculator.EntryCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExecuteEntryValidator implements Validator<EntryExecutionJson, CalculatedEntry> {

    protected EntryRepository entryRepository;

    protected BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    public ExecuteEntryValidator(EntryRepository entryRepository, BudgetCategoryRepository budgetCategoryRepository) {
		this.entryRepository = entryRepository;
		this.budgetCategoryRepository = budgetCategoryRepository;
	}

	@Override
    public CalculatedEntry validate(EntryExecutionJson entryExecution) {
        ensureBudgetCategoryIsValid(entryExecution);
        Entry entry = ensureEntryIsValid(entryExecution);
        CalculatedEntry calculatedEntry = EntryCalculator.processEntry(entry, entryExecution);
        ensureCalculatedEntryIsValid(calculatedEntry);
        return calculatedEntry;
    }

    private Entry ensureEntryIsValid(EntryExecutionJson entryExecution) {
        Entry entry;
        if (entryExecution.getEntryId() == null) {
            throw new BusinessRuleException(ExecuteEntryMessageCode.EXECUTE_ENTRY_ENTRY_IS_BLANK);
        } else {
            entry = entryRepository.findOne(entryExecution.getEntryId());
            if (entry == null) {
                throw new BusinessRuleException(ExecuteEntryMessageCode.EXECUTE_ENTRY_ENTRY_DOES_NOT_EXISTS);
            } else if (entry.getExecutedAt() != null) {
                throw new BusinessRuleException(ExecuteEntryMessageCode.EXECUTE_ENTRY_NOT_AVAILABLE);
            }
        }
        return entry;
    }

    private void ensureBudgetCategoryIsValid(EntryExecutionJson entryExecution) {
        if (entryExecution.getBudgetCategoryId() == null) {
            throw new BusinessRuleException(ExecuteEntryMessageCode.EXECUTE_ENTRY_BUDGET_CATEGORY_IS_BLANK);
        } else if (!budgetCategoryRepository.exists(entryExecution.getBudgetCategoryId())) {
            throw new BusinessRuleException(ExecuteEntryMessageCode.EXECUTE_ENTRY_BUDGET_CATEGORY_DOES_NOT_EXISTS);
        }
    }

    private void ensureCalculatedEntryIsValid(CalculatedEntry calculatedEntry) {
        BusinessRuleException exception = new BusinessRuleException();
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_ADDITION_LESS_THAN_ZERO, calculatedEntry.getAddition());
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_DISCOUNT_LESS_THAN_ZERO, calculatedEntry.getDiscount());
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_NET_VALUE_LESS_THAN_ZERO, calculatedEntry.getNetValue());
        exception.throwException();
    }

    private void ensureIsNotLesserThanZero(BusinessRuleException exception, ErrorMessageCode errorMessage, BigDecimal value) {
        if (isLessThanZero(value)) {
            exception.addMessage(errorMessage);
        }
    }

    private boolean isLessThanZero(BigDecimal testValue) {
        return testValue != null && testValue.compareTo(BigDecimal.ZERO) < 0;
    }

}
