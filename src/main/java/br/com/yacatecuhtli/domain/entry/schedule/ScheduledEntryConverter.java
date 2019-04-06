package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategoryRepository;
import br.com.yacatecuhtli.domain.entry.EntryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEntryConverter extends JsonConverter<ScheduledEntryJson, ScheduledEntry> {

    private BudgetCategoryRepository categoryRepository;

    private EntryConverter entryConverter;

    @Autowired
    public ScheduledEntryConverter(BudgetCategoryRepository categoryRepository, EntryConverter entryConverter) {
		this.categoryRepository = categoryRepository;
		this.entryConverter = entryConverter;
	}

	@Override
    public ScheduledEntry convert(ScheduledEntryJson source) {
        ScheduledEntry scheduledEntry = new ScheduledEntry();
        update(source, scheduledEntry);
        return scheduledEntry;
    }

    @Override
    public void update(ScheduledEntryJson source, ScheduledEntry target) {
        target.setEntry(entryConverter.convert(source.getEntry()));
        target.setCategory(categoryRepository.getOne(source.getCategory().getId()));
        target.setExecuteAt(source.getExecuteAt());
    }

}
