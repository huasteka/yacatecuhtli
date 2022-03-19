package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsValidator;

@Component
public class PaymentTypeValidator extends CrudValidator<PaymentTypeJson> {

    private AccountRepository accountRepository;

    private PaymentTypeRepository paymentTypeRepository;

    private PaymentTermsValidator paymentTermsValidator;

    @Autowired
    public PaymentTypeValidator(AccountRepository accountRepository, PaymentTypeRepository paymentTypeRepository, PaymentTermsValidator paymentTermsValidator) {
        this.accountRepository = accountRepository;
		this.paymentTypeRepository = paymentTypeRepository;
		this.paymentTermsValidator = paymentTermsValidator;
	}

	@Override
    public void executeValidation(PaymentTypeJson object) throws BusinessRuleException {
        ensureThatNameIsNotBlank(object);
        ensureThatPaymentAccountIsNotBlank(object);
        if (object.hasPaymentTerms()) {
            paymentTermsValidator.validate(object.getTerms());
        }
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(PaymentTypeJson paymentTypeJson) {
        BusinessRuleException exception = new BusinessRuleException();
        if (StringUtils.isEmpty(paymentTypeJson.getName())) {
            exception.addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_IS_BLANK);
        } else {
            ensureThatNameIsUnique(exception, paymentTypeJson);
        }
        exception.throwException();
    }

    private void ensureThatNameIsUnique(BusinessRuleException exception, PaymentTypeJson paymentTypeJson) {
        PaymentType exists = paymentTypeRepository.findByNameLikeIgnoreCase(paymentTypeJson.getName());
        if (exists != null && !exists.getId().equals(paymentTypeJson.getId())) {
            exception.addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_NOT_AVAILABLE);
        }
    }

    private void ensureThatPaymentAccountIsNotBlank(PaymentTypeJson paymentTypeJson) {
        BusinessRuleException exception = new BusinessRuleException();
        if (!paymentTypeJson.hasPaymentAccount()) {
            exception.addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_ACCOUNT_IS_BLANK);
        } else {
            Account paymentAccount = accountRepository.findOne(paymentTypeJson.getPaymentAccount().getId());
            if (paymentAccount == null) {
                exception.addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_ACCOUNT_NOT_EXISTS);
            }
        }
        exception.throwException();
    }

    private void ensureThatExists(Integer paymentTypeId) {
        new BusinessRuleException()
                .addMessage(() -> paymentTypeRepository.findOne(paymentTypeId) == null, PaymentTypeMessageCode.PAYMENT_TYPE_DOES_NOT_EXISTS)
                .throwException();
    }

}
