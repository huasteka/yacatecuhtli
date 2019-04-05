package br.com.yacatecuhtli.domain.entry.transfer;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.VoidValidator;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;

@Component
public class TransferredEntryValidator extends VoidValidator<AccountTransferJson> {

    protected AccountRepository accountRepository;

    protected PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public TransferredEntryValidator(AccountRepository accountRepository, PaymentTypeRepository paymentTypeRepository) {
		this.accountRepository = accountRepository;
		this.paymentTypeRepository = paymentTypeRepository;
	}

	@Override
    public void executeValidation(AccountTransferJson accountTransfer) throws BusinessRuleException {
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
