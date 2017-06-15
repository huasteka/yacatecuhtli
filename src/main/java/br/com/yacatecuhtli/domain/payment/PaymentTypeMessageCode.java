package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum PaymentTypeMessageCode implements ErrorMessageCode {

    PAYMENT_TYPE_NAME_IS_BLANK("payment-type.name.error.is-blank"),
    PAYMENT_TYPE_NAME_NOT_AVAILABLE("payment-type.name.error.not-available"),
    PAYMENT_TYPE_DOES_NOT_EXISTS("payment-type.error.not-exists");

    @Getter
    private String messageKey;

    private PaymentTypeMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
