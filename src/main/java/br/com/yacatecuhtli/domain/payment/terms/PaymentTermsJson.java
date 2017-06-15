package br.com.yacatecuhtli.domain.payment.terms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTermsJson {

    protected boolean stagedPayment;

    protected BigDecimal tax;

    protected Integer installmentQuantity;

    protected Integer firstInstallmentTerm;

    protected Integer installmentTerm;

}
