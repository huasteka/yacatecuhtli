package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.domain.account.balance.AbstractAccountBalanceServiceTests;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractEntryServiceTests extends AbstractAccountBalanceServiceTests {

    @SpyBean
    protected EntryValidator entryValidator;

    @SpyBean
    protected EntryConverter entryConverter;

    @SpyBean
    protected EntryService entryService;

}
