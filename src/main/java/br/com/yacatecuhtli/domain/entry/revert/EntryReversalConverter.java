package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.entry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryReversalConverter extends JsonConverter<EntryReversalJson, RevertedEntry> {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected EntryConverter entryConverter;

    @Override
    public RevertedEntry convert(EntryReversalJson source) {
        RevertedEntry revertedEntry = new RevertedEntry();
        update(source, revertedEntry);
        return revertedEntry;
    }

    @Override
    public void update(EntryReversalJson source, RevertedEntry target) {
        Entry entry = entryRepository.findOne(source.getEntryId());
        EntryJson revertedEntry = entry.toJson();
        revertedEntry.setType(EntryType.getReverseType(entry.getType()));
        target.setRevert(entry);
        target.setReverted(entryConverter.convert(revertedEntry));
    }

}
