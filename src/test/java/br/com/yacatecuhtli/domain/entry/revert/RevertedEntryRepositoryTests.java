package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.template.ReversedEntryTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RevertedEntryRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private ReversedEntryRepository reversedEntryRepository;

    @Test
    public void shouldFindReversedEntryByReverseEntryId() {
        ReversedEntry reversedEntry = createPersistedObject(ReversedEntry.class, ReversedEntryTemplateLoader.VALID_REVERSED_ENTRY_TEMPLATE);
        Assert.assertThat(reversedEntry.getReverse(), Matchers.notNullValue());
        Assert.assertThat(reversedEntry.getReverse().getId(), Matchers.notNullValue());

        ReversedEntry result = reversedEntryRepository.findByReverseId(reversedEntry.getReverse().getId());
        Assert.assertThat(result, Matchers.equalTo(reversedEntry));
    }

}
