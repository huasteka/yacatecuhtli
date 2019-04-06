package br.com.yacatecuhtli.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yacatecuhtli.core.controller.ControllerAdapter;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends ControllerAdapter<AccountJson> {

	@Autowired
	public AccountController(AccountService accountService) {
		super(accountService);
	}

}
