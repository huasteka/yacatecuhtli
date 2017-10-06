package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReversedEntryService extends AbstractService {

    @Autowired
    protected ReversedEntryValidator reversedEntryValidator;

    @Autowired
    protected ReversedEntryRepository reversedEntryRepository;

    @Autowired
    protected EntryReversalConverter entryReversalConverter;

    @Autowired
    protected AccountBalanceService accountBalanceService;

    @Transactional
    public void reverse(EntryReversalJson entryReversal) {
        reversedEntryValidator.validate(entryReversal);
        ReversedEntry result = reversedEntryRepository.save(entryReversalConverter.convert(entryReversal));
        accountBalanceService.performOperation(result.getReversed());
    }

}
