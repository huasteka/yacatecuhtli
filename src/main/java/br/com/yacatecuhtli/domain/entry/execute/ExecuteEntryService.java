package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.calculator.CalculatedEntry;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecuteEntryService extends AbstractService {

    @Autowired
    protected ExecuteEntryValidator executeEntryValidator;

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    protected DateService dateService;

    public EntryJson executeEntry(EntryExecutionJson entryExecution) {
        CalculatedEntry calculatedEntry = executeEntryValidator.validate(entryExecution);
        Entry entry = entryRepository.findOne(calculatedEntry.getEntryId());
        calculatedEntry.updateEntry(entry);
        if (entryExecution.getBudgetCategoryId() != null) {
            entry.setCategory(budgetCategoryRepository.findOne(entryExecution.getBudgetCategoryId()));
        }
        entry.setExecutedAt(Optional.ofNullable(entryExecution.getExecuteAt()).orElse(dateService.getNow()));
        return entryRepository.save(entry).toJson();
    }

}
