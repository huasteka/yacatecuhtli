package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.json.JsonRepresentation;
import br.com.yacatecuhtli.domain.account.AccountJson;
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
public class AccountBalanceJson implements JsonRepresentation {

    protected Integer id;

    protected AccountJson account;

    protected Integer year;

    protected Integer month;

    protected BigDecimal balance;

}
