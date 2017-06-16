package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BudgetCategory extends VersionedEntity<BudgetCategoryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private BudgetGroup group;

    @Override
    public BudgetCategoryJson toJson() {
        return BudgetCategoryJson.builder()
                .id(this.id)
                .name(this.name)
                .group(Optional.ofNullable(this.group).orElseGet(BudgetGroup::new).toJson())
                .build();
    }

}
