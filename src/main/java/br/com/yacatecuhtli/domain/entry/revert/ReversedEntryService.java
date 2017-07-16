package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReversedEntryService extends AbstractService {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected ReversedEntryRepository reversedEntryRepository;

    @Autowired
    protected EntryReversalConverter entryReversalConverter;

    @Autowired
    protected AccountBalanceService accountBalanceService;

    @Transactional
    public void reverse(EntryReversalJson entryReversal) {
        validate(entryReversal);
        ReversedEntry result = reversedEntryRepository.save(entryReversalConverter.convert(entryReversal));
        accountBalanceService.performOperation(result.getReversed());
    }

    private void validate(EntryReversalJson entryReversal) {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatEntryExists(entryReversal.getEntryId(), exception);
        exception.throwException();
    }

    private void ensureThatEntryExists(Integer reverseEntryId, BusinessRuleException exception) {
        Entry toReverse = entryRepository.findOne(reverseEntryId);
        if (toReverse == null) {
            exception.addMessage(ReversedEntryMessageCode.REVERSED_ENTRY_ENTRY_DOES_NOT_EXISTS);
        } else {
            ensureThatEntryWasNotReversed(reverseEntryId, exception);
        }
    }

    private void ensureThatEntryWasNotReversed(Integer reverseEntryId, BusinessRuleException exception) {
        ReversedEntry isReversed = reversedEntryRepository.findByReverseId(reverseEntryId);
        if (isReversed != null) {
            exception.addMessage(ReversedEntryMessageCode.REVERSED_ENTRY_ENTRY_CANNOT_BE_REVERSED);
        }
    }

}
