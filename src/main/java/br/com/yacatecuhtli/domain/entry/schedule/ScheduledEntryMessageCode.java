package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;

public enum ScheduledEntryMessageCode implements ErrorMessageCode {

    SCHEDULED_ENTRY_CATEGORY_IS_BLANK("scheduled-entry.category.error.is-blank"),
    SCHEDULED_ENTRY_CATEGORY_UNAVAILABLE("scheduled-entry.category.error.unavailable"),
    SCHEDULED_ENTRY_ENTRY_IS_BLANK("scheduled-entry.entry.error.is-blank"),
    SCHEDULED_ENTRY_ENTRY_HAS_BEEN_EXECUTED("scheduled-entry.entry.error.has-been-executed"),
    SCHEDULED_ENTRY_ENTRY_HAS_BEEN_REVERSED("scheduled-entry.entry.error.has-been-reversed"),
    SCHEDULED_ENTRY_EXECUTE_AT_IS_BLANK("scheduled-entry.execute-at.error.is-blank");

    private String messageKey;

    private ScheduledEntryMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

}
