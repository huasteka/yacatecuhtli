package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferredEntryService extends AbstractService {

    @Autowired
    protected TransferredEntryValidator transferredEntryValidator;

    @Autowired
    protected TransferredEntryRepository transferredEntryRepository;

    @Autowired
    protected AccountTransferConverter accountTransferConverter;

    @Autowired
    protected AccountBalanceService accountBalanceService;

    @Transactional
    public TransferredEntryJson transfer(AccountTransferJson accountTransfer) {
        transferredEntryValidator.validate(accountTransfer);
        TransferredEntry transferred = transferredEntryRepository.save(accountTransferConverter.convert(accountTransfer));
        accountBalanceService.performOperation(transferred.getSource());
        accountBalanceService.performOperation(transferred.getTarget());
        return transferred.toJson();
    }

}
