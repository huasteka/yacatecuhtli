package br.com.yacatecuhtli.domain.entry.revert;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.entry.Entry;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReversedEntry extends VersionedEntity<ReversedEntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Entry reverse;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private Entry reversed;

    @Override
    public ReversedEntryJson toJson() {
        return ReversedEntryJson.builder()
                .id(this.id)
                .reverse(this.reverse.toJson())
                .reversed(this.reversed.toJson())
                .build();
    }

}
