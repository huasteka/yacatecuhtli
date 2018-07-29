package br.com.yacatecuhtli.domain;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SystemMessageCode implements ErrorMessageCode {

    SYSTEM_CONFIGURATION_ERROR("system.error.configuration");

    @Getter
    private String messageKey;

}
