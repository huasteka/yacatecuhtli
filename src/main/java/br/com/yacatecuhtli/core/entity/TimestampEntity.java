package br.com.yacatecuhtli.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.core.json.JsonRepresentation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class TimestampEntity<J extends JsonRepresentation> extends VersionedEntity<J> {

    @Column
    @Getter
    protected Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    protected Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = SystemTime.INSTANCE.getNow();
    }

}
