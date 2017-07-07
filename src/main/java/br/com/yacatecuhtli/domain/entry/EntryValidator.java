package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EntryValidator {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected PaymentTypeRepository paymentTypeRepository;

    public void validate(EntryJson entry) {
        BusinessRuleException exception = new BusinessRuleException();
        ensureThatTypeIsNotBlank(entry, exception);
        validateWithoutType(entry, exception);
        exception.throwException();
    }

    public void validateWithoutType(EntryJson entry, BusinessRuleException exception) {
        ensureThatGrossValueIsGreaterThanZero(entry, exception);
        ensureThatPaymentTypeIsAvailable(entry, exception);
        ensureThatAccountIsAvailable(entry, exception);
    }

    private void ensureThatTypeIsNotBlank(EntryJson entry, BusinessRuleException exception) {
        if (entry.getType() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_TYPE_IS_BLANK);
        }
    }

    private void ensureThatGrossValueIsGreaterThanZero(EntryJson entry, BusinessRuleException exception) {
        if (entry.getGrossValue() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_GROSS_VALUE_IS_BLANK);
        } else if (entry.getGrossValue().compareTo(BigDecimal.ZERO) <= 0) {
            exception.addMessage(EntryMessageCode.ENTRY_GROSS_VALUE_LESS_THAN_ZERO);
        }
    }

    private void ensureThatPaymentTypeIsAvailable(EntryJson entry, BusinessRuleException exception) {
        if (entry.getPaymentType() == null || entry.getPaymentType().getId() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_PAYMENT_TYPE_IS_BLANK);
        } else {
            PaymentType paymentType = paymentTypeRepository.findOne(entry.getPaymentType().getId());
            if (paymentType == null) {
                exception.addMessage(EntryMessageCode.ENTRY_PAYMENT_TYPE_NOT_AVAILABLE);
            }
        }
    }

    private void ensureThatAccountIsAvailable(EntryJson entry, BusinessRuleException exception) {
        if (entry.getAccount() == null || entry.getAccount().getId() == null) {
            exception.addMessage(EntryMessageCode.ENTRY_ACCOUNT_IS_BLANK);
        } else {
            Account account = accountRepository.findOne(entry.getAccount().getId());
            if (account == null) {
                exception.addMessage(EntryMessageCode.ENTRY_ACCOUNT_NOT_AVAILABLE);
            }
        }
    }

}
