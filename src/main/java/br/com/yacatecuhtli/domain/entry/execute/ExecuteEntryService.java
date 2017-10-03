package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Service
public class ExecuteEntryService extends AbstractService {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    protected DateService dateService;

    public EntryJson executeEntry(Integer entryId, EntryExecutionJson entryExecution) {
        BusinessRuleException exception = new BusinessRuleException();
        Entry entry = entryRepository.findOne(entryId);
        validateEntry(exception, entry);
        entry.setTax(entry.calculateTaxValue());
        validateBudgetCategory(exception, entryExecution, entry::setCategory);
        validateValue(exception, entryExecution.getAddition(), ExecuteEntryMessageCode.EXECUTE_ENTRY_ADDITION_LESS_THAN_ZERO, entry::setAddition);
        validateValue(exception, entryExecution.getDiscount(), ExecuteEntryMessageCode.EXECUTE_ENTRY_DISCOUNT_LESS_THAN_ZERO, entry::setDiscount);
        BigDecimal netValue = entry.calculateNetValue();
        validateValue(exception, netValue, ExecuteEntryMessageCode.EXECUTE_ENTRY_NET_VALUE_LESS_THAN_ZERO, entry::setNetValue);
        exception.throwException();
        entry.setExecutedAt(dateService.getNow());
        return entryRepository.save(entry).toJson();
    }

    private void validateEntry(BusinessRuleException exception, Entry entry) {
        if (entry == null) {
            exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_ENTRY_DOES_NOT_EXISTS);
        } else if (entry.getExecutedAt() != null) {
            exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_NOT_AVAILABLE);
        }
    }

    private void validateBudgetCategory(BusinessRuleException exception, EntryExecutionJson entryExecution, Consumer<BudgetCategory> callback) {
        BudgetCategory budgetCategory = budgetCategoryRepository.getOne(entryExecution.getBudgetCategoryId());
        if (budgetCategory == null) {
            exception.addMessage(ExecuteEntryMessageCode.EXECUTE_ENTRY_BUDGET_CATEGORY_IS_BLANK);
        } else {
            callback.accept(budgetCategory);
        }
    }

    private void validateValue(BusinessRuleException exception, BigDecimal value, ExecuteEntryMessageCode errorMessage, Consumer<BigDecimal> callback) {
        if (isLessThanZero(value)) {
            exception.addMessage(errorMessage);
        } else {
            callback.accept(value);
        }
    }

    private boolean isLessThanZero(BigDecimal testValue) {
        return testValue != null && testValue.compareTo(BigDecimal.ZERO) < 0;
    }

}
