package br.com.yacatecuhtli.core.json;

import java.util.Optional;
import java.util.function.Consumer;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.port.ConverterPort;

public abstract class JsonConverter<S extends JsonRepresentation, T extends PersistentEntity<S>> implements ConverterPort<S, T> {

    protected <J extends JsonRepresentation, E extends PersistentEntity<J>> void updateRelationship(EntityRepository<E> repository, J json, Consumer<E> callback) {
        Optional.ofNullable(json)
	        .filter(j -> j.getId() != null)
	        .ifPresent((j) -> callback.accept(repository.findOne(j.getId())));
    }

}
