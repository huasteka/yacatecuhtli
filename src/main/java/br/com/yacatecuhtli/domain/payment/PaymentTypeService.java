package br.com.yacatecuhtli.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yacatecuhtli.core.service.ServiceAdapter;

@Service
public class PaymentTypeService extends ServiceAdapter<PaymentTypeJson, PaymentType> {

    @Autowired
    public PaymentTypeService(PaymentTypeValidator validator, PaymentTypeConverter jsonConverter, PaymentTypeRepository entityRepository) {
        super(validator, jsonConverter, entityRepository);
    }

}
