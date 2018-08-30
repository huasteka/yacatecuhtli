package br.com.yacatecuhtli.core;

import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;

public abstract class AbstractTemplateLoader implements TemplateLoader {

    protected static final Faker FAKER = new Faker();

    protected Object[] generateRandomWords(int quantity, int minLength, int maxLength) {
        Object[] generatedWords = new Object[quantity];
        for (int i = 0; i < quantity; i++) {
            generatedWords[i] = FAKER.lorem().characters(minLength, maxLength);
        }
        return generatedWords;
    }

}
