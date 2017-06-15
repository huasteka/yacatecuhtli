package br.com.yacatecuhtli.domain.payment.terms;

import br.com.yacatecuhtli.core.json.JsonConverter;
import org.springframework.stereotype.Component;

@Component
public class PaymentTermsConverter extends JsonConverter<PaymentTermsJson, PaymentTerms> {

    @Override
    public PaymentTerms convert(PaymentTermsJson source) {
        PaymentTerms paymentTerms = new PaymentTerms();
        update(source, paymentTerms);
        return paymentTerms;
    }

    @Override
    public void update(PaymentTermsJson source, PaymentTerms target) {
        target.setStagedPayment(source.isStagedPayment());
        target.setTax(source.getTax());
        target.setInstallmentQuantity(source.getInstallmentQuantity());
        target.setFirstInstallmentTerm(source.getFirstInstallmentTerm());
        target.setInstallmentTerm(source.getInstallmentTerm());
    }

}
