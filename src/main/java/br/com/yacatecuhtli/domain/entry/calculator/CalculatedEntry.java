package br.com.yacatecuhtli.domain.entry.calculator;

import br.com.yacatecuhtli.domain.entry.Entry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CalculatedEntry {

    private Integer entryId;

    private BigDecimal addition;

    private BigDecimal discount;

    private BigDecimal tax;

    private BigDecimal netValue;

    public void updateEntry(Entry entry) {
        entry.setAddition(addition);
        entry.setDiscount(discount);
        entry.setTax(tax);
        entry.setNetValue(netValue);
    }

}
