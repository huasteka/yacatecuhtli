package br.com.yacatecuhtli.core.json;

import org.springframework.core.convert.converter.Converter;

public abstract class JsonConverter<S, T> implements Converter<S, T> {

    public abstract void update(S source, T target);

}
