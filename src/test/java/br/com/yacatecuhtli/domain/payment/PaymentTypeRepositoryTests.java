package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTypeRepositoryTests extends AbstractRepositorySpec {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    public void shouldFindByName() {
        PaymentType paymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentType savedPaymentType = this.paymentTypeRepository.findByNameLikeIgnoreCase(paymentType.getName().toUpperCase());
        Assert.assertThat(savedPaymentType.getId(), Matchers.equalTo(paymentType.getId()));
    }

}
