package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.json.JsonConverter;
import br.com.yacatecuhtli.domain.account.AccountRepository;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentTypeConverter extends JsonConverter<PaymentTypeJson, PaymentType> {

    @Autowired
    private PaymentTermsConverter paymentTermsConverter;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public PaymentType convert(PaymentTypeJson source) {
        PaymentType paymentType = new PaymentType();
        update(source, paymentType);
        return paymentType;
    }

    @Override
    public void update(PaymentTypeJson source, PaymentType target) {
        target.setName(source.getName());
        Optional.ofNullable(source.getTerms()).map(paymentTermsConverter::convert).ifPresent(target::setTerms);
        updateRelationship(accountRepository, source.getPaymentAccount(), target::setPaymentAccount);
    }

}
