package br.com.yacatecuhtli.domain.payment;

import br.com.yacatecuhtli.core.controller.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypeController extends AbstractCrudController<PaymentType, PaymentTypeJson, PaymentTypeConverter, PaymentTypeRepository, PaymentTypeService> {

    @Autowired
    public PaymentTypeController(PaymentTypeService entityService) {
        super(entityService);
    }

}
