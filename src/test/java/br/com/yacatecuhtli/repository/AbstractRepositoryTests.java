package br.com.yacatecuhtli.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.processor.HibernateProcessor;
import br.com.yacatecuhtli.AbstractApplicationTests;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public abstract class AbstractRepositoryTests extends AbstractApplicationTests {

    @Autowired
    protected TestEntityManager entityManager;

    protected <T> T createPersistedObject(Class<T> entity, String template) {
        Session session = entityManager.getEntityManager().unwrap(Session.class);
        return Fixture.from(entity).uses(new HibernateProcessor(session)).gimme(template);
    }

}
