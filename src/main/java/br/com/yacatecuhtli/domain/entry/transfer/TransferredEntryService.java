package br.com.yacatecuhtli.domain.entry.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;

@Service
public class TransferredEntryService extends AbstractService {

    protected TransferredEntryValidator transferredEntryValidator;

    protected TransferredEntryRepository transferredEntryRepository;

    protected AccountTransferConverter accountTransferConverter;

    protected AccountBalanceService accountBalanceService;

    @Autowired
    public TransferredEntryService(
    		TransferredEntryValidator transferredEntryValidator,
			TransferredEntryRepository transferredEntryRepository, 
			AccountTransferConverter accountTransferConverter,
			AccountBalanceService accountBalanceService
	) {
		this.transferredEntryValidator = transferredEntryValidator;
		this.transferredEntryRepository = transferredEntryRepository;
		this.accountTransferConverter = accountTransferConverter;
		this.accountBalanceService = accountBalanceService;
	}

	@Transactional
    public TransferredEntryJson transfer(AccountTransferJson accountTransfer) {
        transferredEntryValidator.validate(accountTransfer);
        TransferredEntry transferred = transferredEntryRepository.save(accountTransferConverter.convert(accountTransfer));
        accountBalanceService.performOperation(transferred.getSource());
        accountBalanceService.performOperation(transferred.getTarget());
        return transferred.toJson();
    }

}
