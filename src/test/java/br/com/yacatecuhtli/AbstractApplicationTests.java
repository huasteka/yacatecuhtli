package br.com.yacatecuhtli;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;

import java.util.List;

public abstract class AbstractApplicationTests {

    private static final String FIXTURE_TEMPLATE_PACKAGE = "br.com.yacatecuhtli.template";

    @Before
    public void setUp() {
        FixtureFactoryLoader.loadTemplates(FIXTURE_TEMPLATE_PACKAGE);
    }

    protected <T> T createObject(Class<T> entity, String template) {
        return Fixture.from(entity).gimme(template);
    }

    protected <T> List<T> createObjectList(Class<T> entity, String template, Integer quantity) {
        return Fixture.from(entity).gimme(quantity, template);
    }

}
