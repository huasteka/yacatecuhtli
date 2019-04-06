package br.com.yacatecuhtli.domain.entry.revert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yacatecuhtli.core.port.ConverterPort;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryConverter;
import br.com.yacatecuhtli.domain.entry.EntryJson;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import br.com.yacatecuhtli.domain.entry.EntryType;

@Component
public class EntryReversalConverter implements ConverterPort<EntryReversalJson, ReversedEntry> {

    protected EntryRepository entryRepository;

    protected EntryConverter entryConverter;

    protected DateService dateService;

    @Autowired
    public EntryReversalConverter(EntryRepository entryRepository, EntryConverter entryConverter, DateService dateService) {
		this.entryRepository = entryRepository;
		this.entryConverter = entryConverter;
		this.dateService = dateService;
	}

	@Override
    public ReversedEntry convert(EntryReversalJson source) {
        ReversedEntry reversedEntry = new ReversedEntry();
        update(source, reversedEntry);
        return reversedEntry;
    }

    @Override
    public void update(EntryReversalJson source, ReversedEntry target) {
        Entry entry = entryRepository.findOne(source.getEntryId());
        entry.setReversedAt(dateService.getNow());

        EntryJson reversedEntry = entry.toJson();
        reversedEntry.setType(EntryType.getReverseType(entry.getType()));
        reversedEntry.setReversedAt(dateService.getNow());

        target.setReverse(entry);
        target.setReversed(entryConverter.convert(reversedEntry));
    }

}
