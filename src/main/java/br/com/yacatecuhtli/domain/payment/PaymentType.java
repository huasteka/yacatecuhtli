package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.entity.TimestampEntity;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTerms;
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
public class PaymentType extends TimestampEntity<PaymentTypeJson> {

    @Getter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String name;

    @Getter
    @Setter
    @Embedded
    private PaymentTerms terms;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account paymentAccount;

    @Transient
    public boolean hasPaymentTerms() {
        return this.terms != null;
    }

    @Override
    public PaymentTypeJson toJson() {
        return PaymentTypeJson.builder()
                .id(this.id)
                .name(this.name)
                .terms(Optional.ofNullable(this.terms).orElseGet(PaymentTerms::new).toJson())
                .paymentAccount(Optional.ofNullable(this.paymentAccount).orElseGet(Account::new).toJson())
                .build();
    }

}
