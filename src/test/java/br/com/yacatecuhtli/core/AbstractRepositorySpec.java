package br.com.yacatecuhtli.core;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.processor.HibernateProcessor;
import br.com.yacatecuhtli.AbstractApplicationSpec;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

public abstract class AbstractRepositorySpec extends AbstractApplicationSpec {

    @Autowired
    protected TestEntityManager entityManager;

    protected <T> T createPersistedObject(Class<T> entity, String template) {
        return Fixture.from(entity).uses(new HibernateProcessor(getHibernateSession())).gimme(template);
    }

    protected <T> List<T> createPersistedObjectList(Class<T> entity, String template, int quantity) {
        return Fixture.from(entity).uses(new HibernateProcessor(getHibernateSession())).gimme(quantity, template);
    }

    protected <E> E getObject(Class<E> entity, Integer entityId) {
        return getHibernateSession().get(entity, entityId);
    }

    protected <E> void persist(E entity) {
        getHibernateSession().persist(entity);
    }

    private Session getHibernateSession() {
        return entityManager.getEntityManager().unwrap(Session.class);
    }

}
