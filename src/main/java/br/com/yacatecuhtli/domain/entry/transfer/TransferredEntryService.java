package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.account.balance.AccountBalanceService;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferredEntryService extends AbstractService {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected PaymentTypeRepository paymentTypeRepository;

    @Autowired
    protected TransferredEntryRepository transferredEntryRepository;

    @Autowired
    protected AccountTransferConverter accountTransferConverter;

    @Autowired
    protected AccountBalanceService accountBalanceService;

    @Transactional
    public TransferredEntryJson transfer(AccountTransferJson accountTransfer) {
        validate(accountTransfer);
        TransferredEntry transferred = transferredEntryRepository.save(accountTransferConverter.convert(accountTransfer));
        accountBalanceService.performOperation(transferred.getSource());
        accountBalanceService.performOperation(transferred.getTarget());
        return transferred.toJson();
    }

    private void validate(AccountTransferJson accountTransfer) {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatSourceAccountIsNotNull(accountTransfer, exception);
        ensureThatTargetAccountIsNotNull(accountTransfer, exception);
        ensureThatPaymentTypeIsNotNull(accountTransfer, exception);
        ensureThatAmountIsGreaterThanZero(accountTransfer, exception);
        exception.throwException();
    }

    private void ensureThatSourceAccountIsNotNull(AccountTransferJson accountTransfer, BusinessRuleException exception) {
        if (accountTransfer.getSourceAccountId() == null) {
            exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_SOURCE_ACCOUNT_IS_BLANK);
        } else {
            Account source = accountRepository.findOne(accountTransfer.getSourceAccountId());
            if (source == null) {
                exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_SOURCE_ACCOUNT_IS_UNAVAILABLE);
            }
        }
    }

    private void ensureThatTargetAccountIsNotNull(AccountTransferJson accountTransfer, BusinessRuleException exception) {
        if (accountTransfer.getTargetAccountId() == null) {
            exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_TARGET_ACCOUNT_IS_BLANK);
        } else {
            Account target = accountRepository.findOne(accountTransfer.getTargetAccountId());
            if (target == null) {
                exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_TARGET_ACCOUNT_IS_UNAVAILABLE);
            }
        }
    }

    private void ensureThatPaymentTypeIsNotNull(AccountTransferJson accountTransfer, BusinessRuleException exception) {
        if (accountTransfer.getPaymentTypeId() == null) {
            exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_PAYMENT_TYPE_IS_BLANK);
        } else {
            PaymentType paymentType = paymentTypeRepository.findOne(accountTransfer.getPaymentTypeId());
            if (paymentType == null) {
                exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_PAYMENT_TYPE_IS_UNAVAILABLE);
            }
        }
    }

    private void ensureThatAmountIsGreaterThanZero(AccountTransferJson accountTransfer, BusinessRuleException exception) {
        if (accountTransfer.getAmount() == null || BigDecimal.ZERO.compareTo(accountTransfer.getAmount()) >= 0) {
            exception.addMessage(TransferredEntryMessageCode.TRANSFERRED_ENTRY_AMOUNT_LESS_THAN_OR_EQUAL_TO_ZERO);
        }
    }

}
