package br.com.yacatecuhtli.core;

import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

public abstract class AbstractTemplateLoader implements TemplateLoader {

    protected static final Faker FAKER = new Faker();

}
