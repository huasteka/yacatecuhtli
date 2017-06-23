package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum EntryMessageCode implements ErrorMessageCode {

    ENTRY_TYPE_IS_BLANK("entry.type.error.is-blank"),
    ENTRY_GROSS_VALUE_IS_BLANK("entry.gross-value.error.is-blank"),
    ENTRY_GROSS_VALUE_LESS_THAN_ZERO("entry.gross-value.error.less-than-zero"),
    ENTRY_PAYMENT_TYPE_IS_BLANK("entry.payment-type.error.is-blank"),
    ENTRY_PAYMENT_TYPE_NOT_AVAILABLE("entry.payment-type.error.not-available"),
    ENTRY_ACCOUNT_IS_BLANK("entry.account.error.is-blank"),
    ENTRY_ACCOUNT_NOT_AVAILABLE("entry.account.error.not-available");

    @Getter
    private String messageKey;

    private EntryMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
