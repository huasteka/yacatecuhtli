package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BudgetGroup extends VersionedEntity<BudgetGroupJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Override
    public BudgetGroupJson toJson() {
        return BudgetGroupJson.builder().id(this.id).name(this.name).build();
    }
}
