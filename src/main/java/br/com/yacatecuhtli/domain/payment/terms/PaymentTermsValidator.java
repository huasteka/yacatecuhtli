package br.com.yacatecuhtli.domain.payment.terms;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import br.com.yacatecuhtli.core.validator.VoidValidator;
import br.com.yacatecuhtli.domain.payment.PaymentTypeMessageCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentTermsValidator extends VoidValidator<PaymentTermsJson> {

    @Override
    public void executeValidation(PaymentTermsJson object) throws BusinessRuleException {
        BusinessRuleException exception = new BusinessRuleException();
        ensureTaxIsGreaterThanZero(exception, object);
        if (object.isStagedPayment()) {
            ensureStagedPaymentIsValid(exception, object);
        }
        exception.throwException();
    }

    private void ensureTaxIsGreaterThanZero(BusinessRuleException exception, PaymentTermsJson object) {
        if (object.getTax() == null) {
            exception.addMessage(PaymentTypeMessageCode.PAYMENT_TERMS_TAX_IS_NULL);
        } else if (object.getTax().compareTo(BigDecimal.ZERO) < 0) {
            exception.addMessage(PaymentTypeMessageCode.PAYMENT_TERMS_TAX_IS_LESS_THAN_ZERO);
        }
    }

    private void ensureStagedPaymentIsValid(BusinessRuleException exception, PaymentTermsJson object) {
        ensureThatIsNotNullAndGreaterThanZero(
                exception,
                PaymentTypeMessageCode.PAYMENT_TERMS_FIRST_INSTALLMENT_TERM_IS_NULL,
                PaymentTypeMessageCode.PAYMENT_TERMS_FIRST_INSTALLMENT_TERM_IS_LESS_THAN_ZERO,
                object.getFirstInstallmentTerm(),
                true);
        ensureThatIsNotNullAndGreaterThanZero(
                exception,
                PaymentTypeMessageCode.PAYMENT_TERMS_INSTALLMENT_QUANTITY_IS_NULL,
                PaymentTypeMessageCode.PAYMENT_TERMS_INSTALLMENT_QUANTITY_IS_LESS_OR_EQUAL_ZERO,
                object.getInstallmentQuantity(),
                false);
        ensureThatIsNotNullAndGreaterThanZero(
                exception,
                PaymentTypeMessageCode.PAYMENT_TERMS_INSTALLMENT_TERM_IS_NULL,
                PaymentTypeMessageCode.PAYMENT_TERMS_INSTALLMENT_TERM_IS_LESS_OR_EQUAL_ZERO,
                object.getInstallmentTerm(),
                false);
    }

    private void ensureThatIsNotNullAndGreaterThanZero(BusinessRuleException exception, ErrorMessageCode isNullMessage, ErrorMessageCode isLessThanZeroMessage, Integer value, boolean allowZero) {
        if (value == null) {
            exception.addMessage(isNullMessage);
        } else if (value < 0) {
            exception.addMessage(isLessThanZeroMessage);
        } else if (!allowZero && value == 0) {
            exception.addMessage(isLessThanZeroMessage);
        }
    }

}
