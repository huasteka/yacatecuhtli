package br.com.yacatecuhtli.core.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.core.entity.PersistentEntity;
import br.com.yacatecuhtli.core.json.JsonPagedResponse;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.core.port.ConverterPort;
import br.com.yacatecuhtli.core.port.ServicePort;
import br.com.yacatecuhtli.core.port.ValidatorPort;

public abstract class ServiceAdapter<J extends JsonRepresentation, E extends PersistentEntity<J>> extends AbstractService implements ServicePort<J> {

	protected ValidatorPort<J> validator;

	protected ConverterPort<J, E> converter;
	
	protected EntityRepository<E> repository;
	
	public ServiceAdapter(ValidatorPort<J> validator, ConverterPort<J, E> converter, EntityRepository<E> repository) {
		this.validator = validator;
		this.converter = converter;
		this.repository = repository;
	}

	@Transactional
	@Override
	public J save(J jsonRepresentation) {
		validator.validate(jsonRepresentation);
		E entity = repository.save(converter.convert(jsonRepresentation));
		return entity.toJson();
	}

	@Transactional
	@Override
	public void update(Integer entityId, J jsonRepresentation) {
		validator.exists(entityId);
		validator.validate(jsonRepresentation);
		E entity = repository.findOne(entityId);
		converter.update(jsonRepresentation, entity);
		repository.save(entity);
	}

	@Transactional
	@Override
	public void destroy(Integer entityId) {
		validator.exists(entityId);
		repository.delete(entityId);
	}

	@Override
	public J findOne(Integer entityId) {
		E entity = repository.findOne(entityId);
		return cast(Optional.ofNullable(entity).map(E::toJson).orElse(null));
	}

	@Override
	public JsonPagedResponse<J> findAll(Pageable pageable) {
		return getPagedResponse(repository.findAll(pageable));
	}

}
