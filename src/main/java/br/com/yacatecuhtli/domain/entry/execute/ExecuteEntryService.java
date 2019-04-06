package br.com.yacatecuhtli.domain.entry.execute;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.calculator.CalculatedEntry;

@Service
public class ExecuteEntryService extends AbstractService {

    protected ExecuteEntryValidator executeEntryValidator;

    protected EntryRepository entryRepository;

    protected BudgetCategoryRepository budgetCategoryRepository;

    protected DateService dateService;

    @Autowired
    public ExecuteEntryService(
    		ExecuteEntryValidator executeEntryValidator, 
    		EntryRepository entryRepository,
			BudgetCategoryRepository budgetCategoryRepository, 
			DateService dateService
	) {
		this.executeEntryValidator = executeEntryValidator;
		this.entryRepository = entryRepository;
		this.budgetCategoryRepository = budgetCategoryRepository;
		this.dateService = dateService;
	}

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
