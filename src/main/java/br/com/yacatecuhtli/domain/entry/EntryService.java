package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryService extends AbstractService {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected EntryValidator entryValidator;

    @Autowired
    protected EntryConverter entryConverter;

    @Autowired
    protected AccountBalanceService accountBalanceService;

    @Transactional
    public EntryJson deposit(EntryJson entry) {
        return execute(entry, EntryType.DEPOSIT);
    }

    @Transactional
    public EntryJson withdraw(EntryJson entry) {
        return execute(entry, EntryType.WITHDRAW);
    }

    private EntryJson execute(EntryJson entryJson, EntryType type) {
        validate(entryJson, type);
        Entry entry = entryRepository.save(entryConverter.convert(entryJson));
        accountBalanceService.performOperation(entry);
        return entry.toJson();
    }

    @Transactional
    public void batchOperation(List<EntryJson> entries, EntryType type) {
        entries.stream().forEach(entryJson -> validate(entryJson, type));
        List<Entry> result = entries.stream()
                .map(entryConverter::convert)
                .collect(Collectors.toList());
        entryRepository.save(result).forEach(accountBalanceService::performOperation);
    }

    private void validate(EntryJson entryJson, EntryType type) {
        entryJson.setType(type);
        entryValidator.validate(entryJson);
    }

    public JsonPagedResponse<EntryJson> findByAccount(Integer accountId, Pageable pageable) {
        return getPagedResponse(entryRepository.findAllByAccountIdOrderByIssuedAtDesc(accountId, pageable));
    }

    public List<EntryJson> findByCode(String entryCode) {
        List<Entry> entries = entryRepository.findAllByCodeOrderByIssuedAtDesc(entryCode);
        return Optional.ofNullable(entries)
                .orElseGet(ArrayList::new)
                .stream()
                .map(Entry::toJson)
                .collect(Collectors.toList());
    }

}
