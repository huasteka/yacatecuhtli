package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum AccountMessageCode implements ErrorMessageCode {

    ACCOUNT_NAME_IS_BLANK("error.is-blank.account.name"),
    ACCOUNT_NAME_NOT_AVAILABLE("error.not-available.account.name"),
    ACCOUNT_DOES_NOT_EXISTS("error.not-exists.account");

    @Getter
    private String messageKey;

    AccountMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
