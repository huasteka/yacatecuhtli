package br.com.yacatecuhtli.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yacatecuhtli.core.service.ServiceAdapter;

@Service
public class AccountService extends ServiceAdapter<AccountJson, Account> {

	@Autowired
	public AccountService(AccountValidator validator, AccountConverter converter, AccountRepository repository) {
		super(validator, converter, repository);
	}

}
