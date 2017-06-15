package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum AccountMessageCode implements ErrorMessageCode {

    ACCOUNT_NAME_IS_BLANK("account.name.error.is-blank"),
    ACCOUNT_NAME_NOT_AVAILABLE("account.name.error.not-available"),
    ACCOUNT_DOES_NOT_EXISTS("account.error.not-exists");

    @Getter
    private String messageKey;

    private AccountMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
