package br.com.yacatecuhtli.domain.entry.revert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;

@Service
public class ReversedEntryService extends AbstractService {

    protected ReversedEntryValidator reversedEntryValidator;

    protected ReversedEntryRepository reversedEntryRepository;

    protected EntryReversalConverter entryReversalConverter;

    protected AccountBalanceService accountBalanceService;

	@Autowired
    public ReversedEntryService(
    		ReversedEntryValidator reversedEntryValidator,
			ReversedEntryRepository reversedEntryRepository, 
			EntryReversalConverter entryReversalConverter,
			AccountBalanceService accountBalanceService
	) {
		this.reversedEntryValidator = reversedEntryValidator;
		this.reversedEntryRepository = reversedEntryRepository;
		this.entryReversalConverter = entryReversalConverter;
		this.accountBalanceService = accountBalanceService;
	}

	@Transactional
    public void reverse(EntryReversalJson entryReversal) {
        reversedEntryValidator.validate(entryReversal);
        ReversedEntry result = reversedEntryRepository.save(entryReversalConverter.convert(entryReversal));
        accountBalanceService.performOperation(result.getReversed());
    }

}
