package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentTypeMessageCode implements ErrorMessageCode {

    PAYMENT_TYPE_NAME_IS_BLANK("payment-type.name.error.is-blank"),
    PAYMENT_TYPE_NAME_NOT_AVAILABLE("payment-type.name.error.not-available"),
    PAYMENT_TYPE_DOES_NOT_EXISTS("payment-type.error.not-exists"),

    PAYMENT_TERMS_TAX_IS_NULL("payment-type.terms.tax.is-blank"),
    PAYMENT_TERMS_TAX_IS_LESS_THAN_ZERO("payment-type.terms.tax.less-than-zero"),
    PAYMENT_TERMS_FIRST_INSTALLMENT_TERM_IS_NULL("payment-type.terms.first-installment.is-blank"),
    PAYMENT_TERMS_FIRST_INSTALLMENT_TERM_IS_LESS_THAN_ZERO("payment-type.terms.first-installment.less-than-zero"),
    PAYMENT_TERMS_INSTALLMENT_QUANTITY_IS_NULL("payment-type.terms.installment-quantity.is-blank"),
    PAYMENT_TERMS_INSTALLMENT_QUANTITY_IS_LESS_OR_EQUAL_ZERO("payment-type.terms.installment-quantity.less-than-zero"),
    PAYMENT_TERMS_INSTALLMENT_TERM_IS_NULL("payment-type.terms.installment-term.is-blank"),
    PAYMENT_TERMS_INSTALLMENT_TERM_IS_LESS_OR_EQUAL_ZERO("payment-type.terms.installment-term.less-than-zero"),

    PAYMENT_TYPE_ACCOUNT_IS_BLANK("payment-type.account.is-blank"),
    PAYMENT_TYPE_ACCOUNT_NOT_EXISTS("payment-type.account.not-exists");

    @Getter
    private String messageKey;

}
