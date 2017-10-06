package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.Validator;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.calculator.CalculatedEntry;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.calculator.EntryCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExecuteEntryValidator implements Validator<EntryExecutionJson, CalculatedEntry> {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected BudgetCategoryRepository budgetCategoryRepository;

    @Override
    public CalculatedEntry validate(EntryExecutionJson entryExecution) {
        Entry entry = entryRepository.findOne(entryExecution.getEntryId());
        CalculatedEntry calculatedEntry = EntryCalculator.processEntry(entry, entryExecution);
        BusinessRuleException exception = new BusinessRuleException();
        ensureEntryExistsAndIsNotExecuted(exception, entry);
        ensureBudgetCategoryIsNotNull(exception, entryExecution.getBudgetCategoryId());
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_ADDITION_LESS_THAN_ZERO, calculatedEntry.getAddition());
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_DISCOUNT_LESS_THAN_ZERO, calculatedEntry.getDiscount());
        ensureIsNotLesserThanZero(exception, ExecuteEntryMessageCode.EXECUTE_ENTRY_NET_VALUE_LESS_THAN_ZERO, calculatedEntry.getNetValue());
        exception.throwException();
        return calculatedEntry;
    }

    private void ensureEntryExistsAndIsNotExecuted(BusinessRuleException exception, Entry entry) {
        if (entry == null) {
            exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_ENTRY_DOES_NOT_EXISTS);
        } else if (entry.getExecutedAt() != null) {
            exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_NOT_AVAILABLE);
        }
    }

    private void ensureBudgetCategoryIsNotNull(BusinessRuleException exception, Integer budgetCategoryId) {
        if (budgetCategoryId != null) {
            BudgetCategory budgetCategory = budgetCategoryRepository.getOne(budgetCategoryId);
            if (budgetCategory == null) {
                exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_BUDGET_CATEGORY_IS_BLANK);
            }
        }
    }

    private void ensureIsNotLesserThanZero(BusinessRuleException exception, ExecuteEntryMessageCode errorMessage, BigDecimal value) {
        if (isLessThanZero(value)) {
            exception.addMessage(errorMessage);
        }
    }

    private boolean isLessThanZero(BigDecimal testValue) {
        return testValue != null && testValue.compareTo(BigDecimal.ZERO) < 0;
    }


}
