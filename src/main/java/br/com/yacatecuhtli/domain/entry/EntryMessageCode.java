package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum EntryMessageCode implements ErrorMessageCode {

    ENTRY("");

    @Getter
    private String messageKey;

    private EntryMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
