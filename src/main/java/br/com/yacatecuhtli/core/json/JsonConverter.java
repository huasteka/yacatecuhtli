package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class JsonConverter<S, T> implements Converter<S, T> {

    public abstract void update(S source, T target);

    protected <E extends PersistentEntity<J>, J extends JsonRepresentation> void updateRelationship(EntityRepository<E> repository, J json, Consumer<E> callback) {
        Optional.ofNullable(json).filter(j -> j.getId() != null).ifPresent((j) -> callback.accept(repository.findOne(j.getId())));
    }

}
