package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService extends AbstractService {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected EntryValidator entryValidator;

    @Autowired
    protected EntryConverter entryConverter;

    public EntryJson deposit(EntryJson entry) {
        return execute(entry);
    }

    public EntryJson withdraw(EntryJson entry) {
        return execute(entry);
    }

    private EntryJson execute(EntryJson entryJson) {
        entryValidator.validate(entryJson);
        Entry entry = entryConverter.convert(entryJson);
        return entryRepository.save(entry).toJson();
    }

    public JsonPagedResponse<EntryJson> findByAccount(Integer accountId, Pageable pageable) {
        return getPagedResponse(entryRepository.findAllByAccountId(accountId, pageable));
    }

}
