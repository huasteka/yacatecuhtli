package br.com.yacatecuhtli.core.message;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.Getter;

public enum TestMessageCode implements ErrorMessageCode {

    TEST_MESSAGE_CODE("test_message_code"),
    TEST_MESSAGE_CODE_2("test_message_code_2");

    @Getter
    private String messageKey;

    TestMessageCode(String messageKey) {
        this.messageKey = messageKey;
    }

}
