package br.com.yacatecuhtli.core.entity;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
@ToString
@EqualsAndHashCode
public abstract class PersistentEntity {

    public abstract Integer getId();

    public abstract <J extends JsonRepresentation> J toJson();

}
