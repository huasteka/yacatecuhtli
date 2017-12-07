package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.entry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryReversalConverter extends JsonConverter<EntryReversalJson, ReversedEntry> {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected EntryConverter entryConverter;

    @Autowired
    protected DateService dateService;

    @Override
    public ReversedEntry convert(EntryReversalJson source) {
        ReversedEntry reversedEntry = new ReversedEntry();
        update(source, reversedEntry);
        return reversedEntry;
    }

    @Override
    public void update(EntryReversalJson source, ReversedEntry target) {
        Entry entry = entryRepository.findOne(source.getEntryId());
        entry.setReversedAt(SystemTime.INSTANCE.now());

        EntryJson reversedEntry = entry.toJson();
        reversedEntry.setType(EntryType.getReverseType(entry.getType()));
        reversedEntry.setReversedAt(dateService.getNow());

        target.setReverse(entry);
        target.setReversed(entryConverter.convert(reversedEntry));
    }

}