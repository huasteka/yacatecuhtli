package br.com.yacatecuhtli.domain.entry.execute;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;

public enum ExecuteEntryMessageCode implements ErrorMessageCode {

    EXECUTE_ENTRY_ADDITION_LESS_THAN_ZERO("entry-execution.addition.error.less-than-zero"),
    EXECUTE_ENTRY_DISCOUNT_LESS_THAN_ZERO("entry-execution.discount.error.less-than-zero"),
    EXECUTE_ENTRY_NET_VALUE_LESS_THAN_ZERO("entry-execution.net-value.error.less-than-zero"),
    EXECUTE_ENTRY_BUDGET_CATEGORY_IS_BLANK("entry-execution.budget-category.error.is-blank"),
    EXECUTE_ENTRY_ENTRY_DOES_NOT_EXISTS("entry-execution.entry.error.not-exists"),
    EXECUTE_ENTRY_NOT_AVAILABLE("entry-execution.entry.error.not-available");

    private String messageKey;

    private ExecuteEntryMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

}
