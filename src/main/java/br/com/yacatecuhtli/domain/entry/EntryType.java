package br.com.yacatecuhtli.domain.entry;

import lombok.Getter;

public enum EntryType {

    DEPOSIT(true), WITHDRAW(false);

    @Getter
    private boolean type;

    private EntryType(boolean type) {
        this.type = type;
    }
}
