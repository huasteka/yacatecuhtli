package br.com.yacatecuhtli.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yacatecuhtli.core.controller.ControllerAdapter;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypeController extends ControllerAdapter<PaymentTypeJson> {

    @Autowired
    public PaymentTypeController(PaymentTypeService entityService) {
        super(entityService);
    }

}
