package br.com.yacatecuhtli.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.json.JsonResponseMetadata;

@Transactional(readOnly = true)
public abstract class AbstractService implements ServiceInterface {

    protected <Y extends JsonRepresentation, X extends PersistentEntity<Y>> JsonPagedResponse<Y> getPagedResponse(Page<X> page) {
        List<Object> result = page.getContent().stream().map(X::toJson).collect(Collectors.toList());
        JsonResponseMetadata meta = new JsonResponseMetadata(page.getSize(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
        return new JsonPagedResponse<>(cast(result), meta);
    }

    @SuppressWarnings("unchecked")
    protected <T> T cast(Object object) {
        return (T) object;
    }

}
