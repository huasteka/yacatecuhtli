package br.com.yacatecuhtli.domain.entry;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.PaymentTypeJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryJson implements JsonRepresentation {

    protected Integer id;

    protected Date issuedAt;

    protected Date executedAt;

    protected Date reversedAt;

    protected EntryType type;

    protected BigDecimal grossValue;

    protected BigDecimal netValue;

    protected BigDecimal addition;

    protected BigDecimal discount;

    protected String description;

    protected AccountJson account;

    protected PaymentTypeJson paymentType;


}
