package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTypeJson implements JsonRepresentation {

    protected Integer id;

    protected String name;

    protected PaymentTermsJson terms;

    protected AccountJson paymentAccount;

}
