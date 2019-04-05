package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountBalanceMessageCode implements ErrorMessageCode {

    ACCOUNT_BALANCE_OPERATION_TYPE_IS_BLANK("account-balance.operation-type.error.is-blank");

	@Getter
    private String messageKey;

}
