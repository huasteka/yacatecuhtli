package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum AccountBalanceMessageCode implements ErrorMessageCode {

    ACCOUNT_BALANCE_OPERATION_TYPE_IS_BLANK("account-balance.operation-type.error.is-blank");

    @Getter
    private String messageKey;

    private AccountBalanceMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }
}
