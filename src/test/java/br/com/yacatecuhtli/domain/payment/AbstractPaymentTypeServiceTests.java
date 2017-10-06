package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.AbstractRepositorySpec;
import br.com.yacatecuhtli.domain.payment.terms.PaymentTermsConverter;
import org.springframework.boot.test.mock.mockito.SpyBean;

public abstract class AbstractPaymentTypeServiceTests extends AbstractRepositorySpec {

    @SpyBean
    protected PaymentTypeValidator paymentTypeValidator;

    @SpyBean
    protected PaymentTermsConverter paymentTermsConverter;

    @SpyBean
    protected PaymentTypeConverter paymentTypeConverter;

    @SpyBean
    protected PaymentTypeService paymentTypeService;

}
