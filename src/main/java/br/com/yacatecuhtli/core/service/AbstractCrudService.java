package br.com.yacatecuhtli.core.service;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<J extends JsonRepresentation, E extends PersistentEntity<J>, C extends JsonConverter<J, E>, R extends EntityRepository<E>> extends AbstractService {

    protected C jsonConverter;

    protected R entityRepository;

    public AbstractCrudService(C jsonConverter, R entityRepository) {
        this.jsonConverter = jsonConverter;
        this.entityRepository = entityRepository;
    }

    @Transactional
    public J save(J jsonRepresentation) {
        validate(jsonRepresentation);
        return entityRepository.save(jsonConverter.convert(jsonRepresentation)).toJson();
    }

    @Transactional
    public void update(Integer accountId, J jsonRepresentation) {
        validate(jsonRepresentation);
        E entity = entityRepository.findOne(accountId);
        jsonConverter.update(jsonRepresentation, entity);
        entityRepository.save(entity);
    }

    protected abstract void validate(J jsonRepresentation);

    @Transactional
    public void destroy(Integer entityId) {
        validateDelete(entityId);
        entityRepository.delete(entityId);
    }

    protected abstract void validateDelete(Integer entityId);

    public J findOne(Integer entityId) {
        return cast(Optional.ofNullable(entityRepository.findOne(entityId)).map(E::toJson).orElse(null));
    }

    public JsonPagedResponse<J> findAll(Pageable pageable) {
        Page<E> page = entityRepository.findAll(pageable);
        List<Object> result = page.getContent().stream().map(E::toJson).collect(Collectors.toList());
        JsonResponseMetadata meta = new JsonResponseMetadata(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
        return new JsonPagedResponse<>(cast(result), meta);
    }

    @SuppressWarnings("unchecked")
    private <T> T cast(Object object) {
        return (T) object;
    }

}
