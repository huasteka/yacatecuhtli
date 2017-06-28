package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferredEntryService extends AbstractService {

    @Autowired
    protected TransferredEntryRepository transferredEntryRepository;

    @Autowired
    protected AccountTransferConverter accountTransferConverter;

    @Transactional
    public void transfer(AccountTransferJson accountTransfer) {
        validate(accountTransfer);
        transferredEntryRepository.save(accountTransferConverter.convert(accountTransfer));
    }

    private void validate(AccountTransferJson accountTransfer) {

    }

}
