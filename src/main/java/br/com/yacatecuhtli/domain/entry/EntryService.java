package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService extends AbstractService {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected EntryConverter entryConverter;

    public EntryJson deposit(EntryJson entry) {
        return execute(entry);
    }

    public EntryJson withdraw(EntryJson entry) {
        return execute(entry);
    }

    private EntryJson execute(EntryJson entryJson) {
        validate(entryJson);
        Entry entry = entryConverter.convert(entryJson);
        return entryRepository.save(entry).toJson();
    }

    private void validate(EntryJson entry) {

    }

    public JsonPagedResponse<EntryJson> findByAccount(Integer accountId) {
        return null;
    }
}
