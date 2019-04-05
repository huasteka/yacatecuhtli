package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TransferredEntryMessageCode implements ErrorMessageCode {

    TRANSFERRED_ENTRY_SOURCE_ACCOUNT_IS_BLANK("transferred-entry.account.source.error.is-blank"),
    TRANSFERRED_ENTRY_SOURCE_ACCOUNT_IS_UNAVAILABLE("transferred-entry.account.source.error.is-unavailable"),
    TRANSFERRED_ENTRY_TARGET_ACCOUNT_IS_BLANK("transferred-entry.account.target.error.is-blank"),
    TRANSFERRED_ENTRY_TARGET_ACCOUNT_IS_UNAVAILABLE("transferred-entry.account.target.error.is-unavailable"),
    TRANSFERRED_ENTRY_PAYMENT_TYPE_IS_BLANK("transferred-entry.payment-type.error.is-blank"),
    TRANSFERRED_ENTRY_PAYMENT_TYPE_IS_UNAVAILABLE("transferred-entry.payment-type.error.is-unavailable"),
    TRANSFERRED_ENTRY_AMOUNT_LESS_THAN_OR_EQUAL_TO_ZERO("transferred-entry.amount.error.less-than-or-equal-to-zero");

    @Getter
    private String messageKey;

}
