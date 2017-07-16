package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountBalance extends VersionedEntity<AccountBalanceJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Account account;

    @Getter
    @Setter
    private Integer month;

    @Getter
    @Setter
    private Integer year;

    @Getter
    @Setter
    private BigDecimal balance;

    @Override
    public AccountBalanceJson toJson() {
        return AccountBalanceJson.builder()
                .id(this.id)
                .account(Optional.ofNullable(this.account).orElseGet(Account::new).toJson())
                .year(this.year)
                .month(this.month)
                .balance(this.balance)
                .build();
    }

}
