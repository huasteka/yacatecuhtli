package br.com.yacatecuhtli.domain.entry.calculator;

import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.execute.EntryExecutionJson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class EntryCalculator {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.TEN.multiply(BigDecimal.TEN);

    private Entry entry;

    public EntryCalculator(Entry entry) {
        this.entry = entry;
    }

    public static CalculatedEntry processEntry(Entry entry, EntryExecutionJson entryExecution) {
        EntryCalculator entryCalculator = new EntryCalculator(entry);
        BigDecimal tax = entryCalculator.calculateTaxValue();
        BigDecimal netValue = entryCalculator.calculateNetValue(entryExecution.getAddition(), entryExecution.getDiscount(), tax);
        return CalculatedEntry.builder()
                .entryId(entry.getId())
                .addition(entryExecution.getAddition())
                .discount(entryExecution.getDiscount())
                .tax(tax)
                .netValue(netValue)
                .build();
    }

    public BigDecimal calculateTaxValue() {
        if (getEntry().getPaymentType().hasPaymentTerms()) {
            BigDecimal tax = convertPercentageToDecimal(getEntry().getPaymentType().getTerms().getTax());
            return getEntry().getGrossValue().multiply(tax);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calculateNetValue(BigDecimal addition, BigDecimal discount, BigDecimal tax) {
        return getEntry().getGrossValue().add(coalesce(addition)).subtract(coalesce(discount)).subtract(coalesce(tax));
    }

    public BigDecimal calculateNetValue(BigDecimal addition, BigDecimal discount) {
        return calculateNetValue(addition, discount, calculateTaxValue());
    }

    public BigDecimal calculateNetValue() {
        return calculateNetValue(getEntry().getAddition(), getEntry().getDiscount());
    }

    private BigDecimal convertDecimalToPercentage(BigDecimal decimalValue) {
        return coalesce(decimalValue).multiply(ONE_HUNDRED);
    }

    private BigDecimal convertPercentageToDecimal(BigDecimal percentageValue) {
        return coalesce(percentageValue).divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
    }

    private BigDecimal coalesce(BigDecimal value) {
        return Optional.ofNullable(value).orElse(BigDecimal.ZERO);
    }

    public Entry getEntry() {
        return entry;
    }

}
