package br.com.yacatecuhtli.domain.entry.transfer;

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
public class AccountTransferJson {

    private BigDecimal amount;

    private Integer sourceAccountId;

    private Integer targetAccountId;

    private Integer paymentTypeId;

}
