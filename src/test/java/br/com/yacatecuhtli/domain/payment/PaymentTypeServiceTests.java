package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.account.AccountJson;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsConverter;
import br.com.yacatecuhtli.template.AccountTemplateLoader;
import br.com.yacatecuhtli.template.PaymentTypeTemplateLoader;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTypeServiceTests extends AbstractRepositorySpec {

    @SpyBean
    private PaymentTypeConverter paymentTypeConverter;

    @SpyBean
    private PaymentTermsConverter paymentTermsConverter;

    @SpyBean
    private PaymentTypeService paymentTypeService;

    @Test
    public void shouldListPaymentTypes() {
        int paymentTypeQuantity = 10;
        List<PaymentType> paymentTypeList = createPersistedObjectList(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE, paymentTypeQuantity);
        List<PaymentTypeJson> resultSet = paymentTypeService.findAll(new PageRequest(0, 10)).getResult();
        Assert.assertThat(resultSet.size(), Matchers.equalTo(paymentTypeQuantity));
        for (PaymentType paymentType : paymentTypeList) {
            resultSet.stream()
                    .filter(result -> paymentType.getId().compareTo(result.getId()) == 0)
                    .forEach(result -> Assert.assertThat(result, Matchers.samePropertyValuesAs(paymentType.toJson())));
        }
    }

    @Test
    public void shouldFindPaymentTypeById() {
        PaymentType savedPaymentType = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        int savedPaymentTypeId = savedPaymentType.getId();
        PaymentTypeJson result = paymentTypeService.findOne(savedPaymentTypeId);
        Assert.assertThat(result.getId(), Matchers.equalTo(savedPaymentTypeId));
        Assert.assertThat(result, Matchers.samePropertyValuesAs(savedPaymentType.toJson()));
    }

    @Test
    public void shouldSavePaymentType() {
        AccountJson validAccount = createPersistedObject(Account.class, AccountTemplateLoader.VALID_ACCOUNT_TEMPLATE).toJson();
        PaymentTypeJson payload = createObject(PaymentTypeJson.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        payload.setPaymentAccount(validAccount);
        Assert.assertThat(payload.getId(), Matchers.nullValue());
        PaymentTypeJson savedPaymentType = paymentTypeService.save(payload);
        Assert.assertThat(savedPaymentType, Matchers.notNullValue());
        Assert.assertThat(savedPaymentType.getId(), Matchers.notNullValue());
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

    @Test
    public void shouldUpdatePaymentType() {
        PaymentTypeJson payload = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE).toJson();
        String oldName = payload.getName();
        payload.setName(new Faker().lorem().characters(15));
        paymentTypeService.update(payload.getId(), payload);
        PaymentType updatedPaymentType = getObject(PaymentType.class, payload.getId());
        Assert.assertThat(oldName, Matchers.not(Matchers.equalToIgnoringCase(updatedPaymentType.getName())));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateDuplicatedPaymentType() {
        PaymentType original = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentType duplicated = createPersistedObject(PaymentType.class, PaymentTypeTemplateLoader.VALID_PAYMENT_TYPE_TEMPLATE);
        PaymentTypeJson payload = duplicated.toJson();
        payload.setName(original.getName());
        paymentTypeService.update(duplicated.getId(), payload);
    }

}
