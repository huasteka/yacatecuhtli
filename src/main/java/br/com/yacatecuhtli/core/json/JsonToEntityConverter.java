package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.entity.PersistentEntity;
import org.springframework.core.convert.converter.Converter;

public abstract class JsonToEntityConverter<S extends JsonRepresentation, T extends PersistentEntity> implements Converter<S, T> {

    public abstract void update(S source, T target);

}
