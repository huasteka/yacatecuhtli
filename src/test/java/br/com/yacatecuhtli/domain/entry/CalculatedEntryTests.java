package br.com.yacatecuhtli.domain.entry;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.yacatecuhtli.domain.entry.calculator.CalculatedEntry;
import br.com.yacatecuhtli.domain.entry.calculator.EntryCalculator;
import br.com.yacatecuhtli.domain.entry.execute.EntryExecutionJson;
import br.com.yacatecuhtli.domain.payment.PaymentType;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTerms;

@RunWith(SpringRunner.class)
public class CalculatedEntryTests {

	@Test
	public void shouldCalculateEntryValues() {
		PaymentTerms paymentTerm = new PaymentTerms();
		paymentTerm.setTax(new BigDecimal("10.00"));
		PaymentType paymentType = new PaymentType();
		paymentType.setTerms(paymentTerm);

		Entry mock = new Entry();
		mock.setPaymentType(paymentType);
		mock.setGrossValue(new BigDecimal("246.80"));
		mock.setDiscount(new BigDecimal("36.90"));
		mock.setAddition(new BigDecimal("12.30"));

		EntryCalculator calculator = new EntryCalculator(mock);
		
		BigDecimal expectedTax = createFixedDecimal("24.68");
		BigDecimal expectedTotal = createFixedDecimal("197.52");
		Assert.assertEquals(expectedTax, calculator.calculateTaxValue());
		Assert.assertEquals(expectedTotal, calculator.calculateNetValue());
	}
	
	@Test
	public void shouldProduceEntryExecution() {
		CalculatedEntry expected = new CalculatedEntry();
		expected.setAddition(createFixedDecimal("12.30"));
		expected.setDiscount(createFixedDecimal("36.90"));
		expected.setTax(createFixedDecimal("24.68"));
		expected.setNetValue(createFixedDecimal("197.52"));
		
		PaymentTerms paymentTerm = new PaymentTerms();
		paymentTerm.setTax(new BigDecimal("10.00"));
		PaymentType paymentType = new PaymentType();
		paymentType.setTerms(paymentTerm);
		
		Entry mock = new Entry();
		mock.setPaymentType(paymentType);
		mock.setGrossValue(new BigDecimal("246.80"));
		EntryExecutionJson json = new EntryExecutionJson();
		json.setAddition(expected.getAddition());
		json.setDiscount(expected.getDiscount());
		
		CalculatedEntry result = EntryCalculator.processEntry(mock, json);
		Assert.assertEquals(expected.getAddition(), result.getAddition());
		Assert.assertEquals(expected.getDiscount(), result.getDiscount());
		Assert.assertEquals(expected.getTax(), result.getTax());
		Assert.assertEquals(expected.getNetValue(), result.getNetValue());
	}
	
	private BigDecimal createFixedDecimal(String value) {
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
	}
	
}
