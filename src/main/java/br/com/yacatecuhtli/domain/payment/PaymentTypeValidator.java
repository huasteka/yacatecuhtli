package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PaymentTypeValidator extends CrudValidator<PaymentTypeJson> {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public void executeValidation(PaymentTypeJson object) throws BusinessRuleException {
        ensureThatNameIsNotBlank(object);
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(PaymentTypeJson paymentTypeJson) {
        if (StringUtils.isEmpty(paymentTypeJson.getName())) {
            new BusinessRuleException().addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(paymentTypeJson);
        }
    }

    private void ensureThatNameIsUnique(PaymentTypeJson paymentTypeJson) {
        PaymentType exists = paymentTypeRepository.findByNameLikeIgnoreCase(paymentTypeJson.getName());
        if (exists != null && !exists.getId().equals(paymentTypeJson.getId())) {
            new BusinessRuleException().addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_NOT_AVAILABLE).throwException();
        }
    }

    private void ensureThatExists(Integer paymentTypeId) {
        new BusinessRuleException()
                .addMessage(() -> paymentTypeRepository.findOne(paymentTypeId) == null, PaymentTypeMessageCode.PAYMENT_TYPE_DOES_NOT_EXISTS)
                .throwException();
    }

}
