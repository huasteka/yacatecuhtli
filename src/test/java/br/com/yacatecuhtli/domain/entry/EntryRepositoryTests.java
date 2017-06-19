package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private EntryRepository entryRepository;

}
