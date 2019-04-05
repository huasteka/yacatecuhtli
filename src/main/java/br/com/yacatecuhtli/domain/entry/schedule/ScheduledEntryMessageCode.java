package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ScheduledEntryMessageCode implements ErrorMessageCode {

    SCHEDULED_ENTRY_CATEGORY_IS_BLANK("scheduled-entry.category.error.is-blank"),
    SCHEDULED_ENTRY_CATEGORY_UNAVAILABLE("scheduled-entry.category.error.unavailable"),
    SCHEDULED_ENTRY_ENTRY_IS_BLANK("scheduled-entry.entry.error.is-blank"),
    SCHEDULED_ENTRY_ENTRY_HAS_BEEN_EXECUTED("scheduled-entry.entry.error.has-been-executed"),
    SCHEDULED_ENTRY_ENTRY_HAS_BEEN_REVERSED("scheduled-entry.entry.error.has-been-reversed"),
    SCHEDULED_ENTRY_EXECUTE_AT_IS_BLANK("scheduled-entry.execute-at.error.is-blank");

	@Getter
    private String messageKey;

}
