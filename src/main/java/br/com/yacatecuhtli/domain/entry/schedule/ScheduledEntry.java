package br.com.yacatecuhtli.domain.entry.schedule;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
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
public class ScheduledEntry extends VersionedEntity<ScheduledEntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private Entry entry;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private BudgetCategory category;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date executeAt;

    @Override
    public ScheduledEntryJson toJson() {
        return ScheduledEntryJson.builder()
                .id(this.id)
                .entry(this.entry.toJson())
                .category(this.category.toJson())
                .executeAt(this.executeAt)
                .build();
    }

}
