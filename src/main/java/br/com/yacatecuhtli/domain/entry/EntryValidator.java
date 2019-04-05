package br.com.yacatecuhtli.domain.entry;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.VoidValidator;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;

@Component
public class EntryValidator extends VoidValidator<EntryJson> {

    protected AccountRepository accountRepository;

    protected PaymentTypeRepository paymentTypeRepository;
    
    @Autowired
    public EntryValidator(AccountRepository accountRepository, PaymentTypeRepository paymentTypeRepository) {
		this.accountRepository = accountRepository;
		this.paymentTypeRepository = paymentTypeRepository;
	}

	@Override
    public void executeValidation(EntryJson entry) {
        BusinessRuleException exception = new BusinessRuleException();
        executeValidation(exception, entry);
        exception.throwException();
    }

    public void executeValidation(BusinessRuleException exception, EntryJson entry) {
        ensureThatTypeIsNotBlank(exception, entry);
        ensureThatGrossValueIsGreaterThanZero(exception, entry);
        ensureThatPaymentTypeIsAvailable(exception, entry);
        ensureThatAccountIsAvailable(exception, entry);
    }

    private void ensureThatTypeIsNotBlank(BusinessRuleException exception, EntryJson entry) {
        if (entry.getType() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_TYPE_IS_BLANK);
        }
    }

    private void ensureThatGrossValueIsGreaterThanZero(BusinessRuleException exception, EntryJson entry) {
        if (entry.getGrossValue() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_GROSS_VALUE_IS_BLANK);
        } else if (entry.getGrossValue().compareTo(BigDecimal.ZERO) <= 0) {
            exception.addMessage(EntryMessageCode.ENTRY_GROSS_VALUE_LESS_THAN_ZERO);
        }
    }

    private void ensureThatPaymentTypeIsAvailable(BusinessRuleException exception, EntryJson entry) {
        if (entry.getPaymentType() == null || entry.getPaymentType().getId() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_PAYMENT_TYPE_IS_BLANK);
        } else if (!paymentTypeRepository.exists(entry.getPaymentType().getId())) {
            exception.addMessage(EntryMessageCode.ENTRY_PAYMENT_TYPE_NOT_AVAILABLE);
        }
    }

    private void ensureThatAccountIsAvailable(BusinessRuleException exception, EntryJson entry) {
        if (entry.getAccount() == null || entry.getAccount().getId() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_ACCOUNT_IS_BLANK);
        } else if (!accountRepository.exists(entry.getAccount().getId())) {
            exception.addMessage(EntryMessageCode.ENTRY_ACCOUNT_NOT_AVAILABLE);
        }
    }

}
