package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.controller.AbstractCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends AbstractCrudController<AccountJson, Account, AccountConverter, AccountRepository, AccountValidator, AccountService> {

    @Autowired
    public AccountController(AccountService entityService) {
        super(entityService);
    }

}
