package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReversedEntryService extends AbstractService {

    @Autowired
    protected ReversedEntryRepository reversedEntryRepository;

    @Autowired
    protected EntryReversalConverter entryReversalConverter;

    @Transactional
    public void reverse(EntryReversalJson entryReversal) {
        ensureThatEntryExists(entryReversal);
        reversedEntryRepository.save(entryReversalConverter.convert(entryReversal));
    }

    private void ensureThatEntryExists(EntryReversalJson entryReversal) {

    }

}
