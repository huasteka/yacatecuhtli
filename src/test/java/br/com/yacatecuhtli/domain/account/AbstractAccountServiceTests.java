package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractAccountServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected AccountValidator accountValidator;

    @SpyBean
    protected AccountConverter accountConverter;

    @SpyBean
    protected AccountService accountService;

}
