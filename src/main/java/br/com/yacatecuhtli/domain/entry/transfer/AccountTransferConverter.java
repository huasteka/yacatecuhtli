package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.core.service.DateService;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountTransferConverter extends JsonConverter<AccountTransferJson, TransferredEntry> {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected PaymentTypeRepository paymentTypeRepository;

    @Autowired
    protected DateService dateService;

    @Override
    public TransferredEntry convert(AccountTransferJson source) {
        TransferredEntry transferredEntry = new TransferredEntry();
        update(source, transferredEntry);
        return transferredEntry;
    }

    @Override
    public void update(AccountTransferJson source, TransferredEntry target) {
        target.setSource(createEntry(source.getSourceAccountId(), source.getPaymentTypeId(), source.getAmount(), EntryType.WITHDRAW));
        target.setTarget(createEntry(source.getTargetAccountId(), source.getPaymentTypeId(), source.getAmount(), EntryType.DEPOSIT));
        target.setTransferredAt(dateService.getNow());
    }

    private Entry createEntry(Integer accountId, Integer paymentTypeId, BigDecimal amount, EntryType type) {
        Entry source = new Entry();
        source.setAccount(accountRepository.findOne(accountId));
        source.setPaymentType(paymentTypeRepository.findOne(paymentTypeId));
        source.setGrossValue(amount);
        source.setNetValue(amount);
        source.setExecutedAt(dateService.getNow());
        source.setType(type);
        return source;
    }

}
