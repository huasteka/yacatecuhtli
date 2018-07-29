package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountMessageCode implements ErrorMessageCode {

    ACCOUNT_CODE_IS_BLANK("account.code.error.is-blank"),
    ACCOUNT_CODE_NOT_AVAILABLE("account.code.error.not-available"),
    ACCOUNT_NAME_IS_BLANK("account.name.error.is-blank"),
    ACCOUNT_NAME_NOT_AVAILABLE("account.name.error.not-available"),
    ACCOUNT_DOES_NOT_EXISTS("account.error.not-exists");

    @Getter
    private String messageKey;

}
