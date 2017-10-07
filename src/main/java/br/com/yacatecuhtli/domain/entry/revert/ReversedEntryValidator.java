package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.VoidValidator;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReversedEntryValidator extends VoidValidator<EntryReversalJson> {

    @Autowired
    protected EntryRepository entryRepository;

    @Autowired
    protected ReversedEntryRepository reversedEntryRepository;

    @Override
    public void executeValidation(EntryReversalJson entryReversal) throws BusinessRuleException {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatEntryExists(exception, entryReversal.getEntryId());
        exception.throwException();
    }

    private void ensureThatEntryExists(BusinessRuleException exception, Integer reverseEntryId) {
        Entry toReverse = entryRepository.findOne(reverseEntryId);
        if (toReverse == null) {
            exception.addMessage(ReversedEntryMessageCode.REVERSED_ENTRY_ENTRY_DOES_NOT_EXISTS);
        } else {
            ensureThatEntryWasNotReversed(exception, reverseEntryId);
        }
    }

    private void ensureThatEntryWasNotReversed(BusinessRuleException exception, Integer reverseEntryId) {
        ReversedEntry isReversed = reversedEntryRepository.findByReverseId(reverseEntryId);
        if (isReversed != null) {
            exception.addMessage(ReversedEntryMessageCode.REVERSED_ENTRY_ENTRY_CANNOT_BE_REVERSED);
        }
    }

}
