package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.template.EntryTemplateLoader;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private EntryRepository entryRepository;

    @Test
    public void shouldPaginateFindByAccount() {
        List<Entry> entryList = createPersistedObjectList(Entry.class, EntryTemplateLoader.VALID_ENTRY_TEMPLATE, 12);
        Date issuedDate = entryList.get(0).getIssuedAt();
        Account mainAccount = entryList.get(0).getAccount();
        entryList.stream().forEach(entry -> nomalizeCommonValues(entry, mainAccount, issuedDate));
        Page<Entry> resultSet = entryRepository.findAllByAccountIdOrderByIssuedAtDesc(mainAccount.getId(), new PageRequest(1, 10));
        Assert.assertThat(resultSet, Matchers.notNullValue());
        Assert.assertThat(resultSet.getNumber(), Matchers.equalTo(1));
        Assert.assertThat(resultSet.getTotalPages(), Matchers.equalTo(2));
        Assert.assertThat(resultSet.getTotalElements(), Matchers.equalTo(12L));
        Assert.assertThat(resultSet.getContent(), Matchers.equalTo(entryList.subList(10, 12)));
    }

    private void nomalizeCommonValues(Entry entry, Account account, Date issuedDate) {
        entry.setAccount(account);
        entry.setIssuedAt(DateUtils.addDays(issuedDate, 1));
        persist(entry);
    }

}
