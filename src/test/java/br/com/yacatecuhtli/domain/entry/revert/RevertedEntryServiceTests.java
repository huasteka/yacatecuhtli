package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.domain.account.balance.AbstractAccountBalanceServiceTests;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryConverter;
import br.com.yacatecuhtli.domain.entry.EntryType;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RevertedEntryServiceTests extends AbstractAccountBalanceServiceTests {

    @SpyBean
    protected ReversedEntryValidator reversedEntryValidator;

    @SpyBean
    protected EntryConverter entryConverter;

    @SpyBean
    protected EntryReversalConverter entryReversalConverter;

    @SpyBean
    protected ReversedEntryService reversedEntryService;

    @Autowired
    protected ReversedEntryRepository reversedEntryRepository;

    @Test
    public void shouldReverseEntry() {
        Entry reverseEntry = createPersistedObject(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE);
        EntryReversalJson payload = new EntryReversalJson(reverseEntry.getId());
        reversedEntryService.reverse(payload);
        ReversedEntry reversedEntry = reversedEntryRepository.findByReverseId(reverseEntry.getId());

        Assert.assertThat(reversedEntry, Matchers.notNullValue());
        Assert.assertThat(reversedEntry.getReverse(), Matchers.notNullValue());
        Assert.assertThat(reversedEntry.getReverse().getReversedAt(), Matchers.notNullValue());
        Assert.assertThat(reversedEntry.getReversed(), Matchers.notNullValue());
        Assert.assertThat(reversedEntry.getReversed().getReversedAt(), Matchers.notNullValue());

        Assert.assertThat(reversedEntry.getReversed().getType(), Matchers.equalTo(EntryType.getReverseType(reverseEntry.getType())));
        Assert.assertThat(reversedEntry.getReversed().getAccount(), Matchers.equalTo(reverseEntry.getAccount()));
        Assert.assertThat(reversedEntry.getReversed().getPaymentType(), Matchers.equalTo(reverseEntry.getPaymentType()));

        Assert.assertThat(reversedEntry.getReversed().getGrossValue(), Matchers.equalTo(reverseEntry.getGrossValue()));
        Assert.assertThat(reversedEntry.getReversed().getNetValue(), Matchers.equalTo(reverseEntry.getNetValue()));
        Assert.assertThat(reversedEntry.getReversed().getAddition(), Matchers.equalTo(reverseEntry.getAddition()));
        Assert.assertThat(reversedEntry.getReversed().getDiscount(), Matchers.equalTo(reverseEntry.getDiscount()));
    }

}
