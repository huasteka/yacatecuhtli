package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.core.entity.VersionedEntity;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.budget.category.BudgetCategory;
import br.com.yacatecuhtli.domain.entry.calculator.EntryCalculator;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Entity
@Table
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Entry extends VersionedEntity<EntryJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(length = 100)
    private String code;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date issuedAt;

    @Getter
    @Setter
    @Column
    private Date executedAt;

    @Getter
    @Setter
    @Column
    private Date reversedAt;

    @Getter
    @Setter
    @Enumerated(value = EnumType.STRING)
    private EntryType type;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal grossValue;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal netValue;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal tax;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal addition;

    @Getter
    @Setter
    @Column(precision = 10, scale = 2)
    private BigDecimal discount;

    @Getter
    @Setter
    @Column(length = 500)
    private String description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PaymentType paymentType;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private BudgetCategory category;

    @Transient
    public BigDecimal getTotal() {
        return Optional.ofNullable(this.netValue).orElse(new EntryCalculator(this).calculateNetValue());
    }

    @Override
    public EntryJson toJson() {
        return EntryJson.builder()
                .id(this.id)
                .code(this.code)
                .issuedAt(this.issuedAt)
                .executedAt(this.executedAt)
                .reversedAt(this.reversedAt)
                .type(this.type)
                .grossValue(this.grossValue)
                .netValue(this.netValue)
                .addition(this.addition)
                .discount(this.discount)
                .description(this.description)
                .account(Optional.ofNullable(this.account).orElseGet(Account::new).toJson())
                .paymentType(Optional.ofNullable(this.paymentType).orElseGet(PaymentType::new).toJson())
                .category(Optional.ofNullable(this.category).orElseGet(BudgetCategory::new).toJson())
                .build();
    }

    @PrePersist
    protected void onCreate() {
        this.issuedAt = SystemTime.INSTANCE.now();
    }

}
