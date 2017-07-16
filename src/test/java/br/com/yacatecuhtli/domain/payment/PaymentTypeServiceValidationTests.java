package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTypeServiceValidationTests extends AbstractPaymentTypeServiceTests {

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveBlankName() {
        PaymentTypeJson invalid = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        invalid.setName(null);
        paymentTypeService.save(invalid);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotSaveDuplicatedName() {
        PaymentType original = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentTypeJson payload = PaymentTypeJson.builder()
                .name(original.getName())
                .terms(original.getTerms().toJson())
                .paymentAccount(original.getPaymentAccount().toJson())
                .build();
        paymentTypeService.save(payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedPaymentType() {
        PaymentType original = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentType duplicated = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentTypeJson payload = duplicated.toJson();
        payload.setName(original.getName());
        paymentTypeService.update(duplicated.getId(), payload);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteIfPaymentTypeNotExists() {
        paymentTypeService.destroy(999);
    }

}
