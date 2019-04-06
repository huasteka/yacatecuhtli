package br.com.yacatecuhtli.domain.entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EntryType {

    DEPOSIT(true), WITHDRAW(false);

    @Getter
    private boolean type;

    public static EntryType getReverseType(EntryType type) {
        return type.isType() ? EntryType.WITHDRAW : EntryType.DEPOSIT;
    }

}
