package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractCrudService<AccountJson, Account, AccountConverter, AccountRepository, AccountValidator> {

    @Autowired
    public AccountService(AccountConverter jsonConverter, AccountRepository entityRepository, AccountValidator validator) {
        super(jsonConverter, entityRepository, validator);
    }

}
