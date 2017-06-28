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
public class RevertedEntry extends VersionedEntity<RevertedEntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Entry revert;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private Entry reverted;

    @Override
    public RevertedEntryJson toJson() {
        return RevertedEntryJson.builder()
                .id(this.id)
                .revert(this.revert.toJson())
                .reverted(this.reverted.toJson())
                .build();
    }

}
