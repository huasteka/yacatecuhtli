package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PaymentTypeService extends AbstractCrudService<PaymentType, PaymentTypeJson, PaymentTypeConverter, PaymentTypeRepository> {

    @Autowired
    public PaymentTypeService(PaymentTypeConverter jsonConverter, PaymentTypeRepository entityRepository) {
        super(jsonConverter, entityRepository);
    }

    @Override
    protected void validate(PaymentTypeJson jsonRepresentation) {
        ensureThatNameIsNotBlank(jsonRepresentation);
    }

    private void ensureThatNameIsNotBlank(PaymentTypeJson paymentTypeJson) {
        if (StringUtils.isEmpty(paymentTypeJson.getName())) {
            new BusinessRuleException().addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(paymentTypeJson);
        }
    }

    private void ensureThatNameIsUnique(PaymentTypeJson paymentTypeJson) {
        PaymentType exists = entityRepository.findByNameLikeIgnoreCase(paymentTypeJson.getName());
        if (exists != null && !exists.getId().equals(paymentTypeJson.getId())) {
            new BusinessRuleException().addMessage(PaymentTypeMessageCode.PAYMENT_TYPE_NAME_NOT_AVAILABLE).throwException();
        }
    }

    @Override
    protected void validateDelete(Integer entityId) {
        ensureThatExists(entityId);
    }

    private void ensureThatExists(Integer paymentTypeId) {
        new BusinessRuleException()
                .addMessage(() -> entityRepository.findOne(paymentTypeId) == null, PaymentTypeMessageCode.PAYMENT_TYPE_DOES_NOT_EXISTS)
                .throwException();
    }

}
