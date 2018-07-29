package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.entity.TimestampEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Account extends TimestampEntity<AccountJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, length = 10)
    private String code;

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String name;

    @Override
    public AccountJson toJson() {
        return AccountJson.builder().id(this.id).code(this.code).name(this.name).build();
    }

}
