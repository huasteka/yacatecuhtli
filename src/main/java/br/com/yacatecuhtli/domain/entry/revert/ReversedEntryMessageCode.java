package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReversedEntryMessageCode implements ErrorMessageCode {

    REVERSED_ENTRY_ENTRY_DOES_NOT_EXISTS("reversed-entry.entry.error.not-exists"),
    REVERSED_ENTRY_ENTRY_CANNOT_BE_REVERSED("reversed-entry.entry.error.cannot-be-reversed");

    @Getter
    private String messageKey;

}
