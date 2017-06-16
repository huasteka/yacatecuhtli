package br.com.yacatecuhtli.core.entity;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class VersionedEntity<J extends JsonRepresentation> extends PersistentEntity<J> {

    @Version
    @Column(nullable = false)
    protected Integer rowVersion;

}
