package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService extends AbstractCrudService<PaymentTypeJson, PaymentType, PaymentTypeConverter, PaymentTypeRepository, PaymentTypeValidator> {

    @Autowired
    public PaymentTypeService(PaymentTypeConverter jsonConverter, PaymentTypeRepository entityRepository, PaymentTypeValidator validator) {
        super(jsonConverter, entityRepository, validator);
    }

}
