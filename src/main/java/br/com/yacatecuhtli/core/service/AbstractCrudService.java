package br.com.yacatecuhtli.core.service;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        return getPagedResponse(entityRepository.findAll(pageable));
    }

}
