package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.core.service.DateService;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractAccountBalanceServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected AccountBalanceService accountBalanceService;

    @SpyBean
    protected DateService dateService;

}
