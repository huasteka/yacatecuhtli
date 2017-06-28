package br.com.yacatecuhtli.domain.entry.transfer;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.entry.Entry;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TransferredEntry extends VersionedEntity<TransferredEntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private Entry source;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private Entry target;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date transferredAt;

    @Override
    public TransferredEntryJson toJson() {
        return TransferredEntryJson.builder()
                .id(this.id)
                .source(this.source.toJson())
                .target(this.target.toJson())
                .transferredAt(this.transferredAt)
                .build();
    }

}
