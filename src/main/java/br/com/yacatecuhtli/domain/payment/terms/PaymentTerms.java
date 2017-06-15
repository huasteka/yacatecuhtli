package br.com.yacatecuhtli.domain.payment.terms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PaymentTerms {

    @Column
    protected boolean stagedPayment;

    /**
     * Credit card service's tax discount
     */
    @Column(precision = 10, scale = 2)
    protected BigDecimal tax;

    /**
     * Max quantity of intallments this payment allows
     */
    @Column
    protected Integer installmentQuantity;

    /**
     * First installment's term in days
     */
    @Column
    protected Integer firstInstallmentTerm;

    /**
     * Term in days between installments
     */
    @Column
    protected Integer installmentTerm;

    public PaymentTermsJson toJson() {
        return PaymentTermsJson.builder()
                .stagedPayment(this.stagedPayment)
                .tax(this.tax)
                .installmentQuantity(this.installmentQuantity)
                .firstInstallmentTerm(this.firstInstallmentTerm)
                .installmentTerm(this.installmentTerm)
                .build();
    }

}
