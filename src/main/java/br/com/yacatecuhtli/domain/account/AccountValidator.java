package br.com.yacatecuhtli.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;

@Component
public class AccountValidator extends CrudValidator<AccountJson> {

	private AccountRepository accountRepository;

	@Autowired
	public AccountValidator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void executeValidation(AccountJson accountJson) throws BusinessRuleException {
		ensureThatIsValid(accountJson);
	}

	@Override
	public void exists(Integer entityId) throws BusinessRuleException {
		ensureThatExists(entityId);
	}

	private void ensureThatIsValid(AccountJson accountJson) {
		BusinessRuleException exception = new BusinessRuleException();
		ensureThatCodeIsNotBlank(exception, accountJson);
		ensureThatNameIsNotBlank(exception, accountJson);
		exception.throwException();
	}

	private void ensureThatCodeIsNotBlank(BusinessRuleException exception, AccountJson accountJson) {
		if (StringUtils.isEmpty(accountJson.getCode())) {
			exception.addMessage(AccountMessageCode.ACCOUNT_CODE_IS_BLANK);
		} else {
			ensureThatCodeIsUnique(exception, accountJson);
		}
	}

	private void ensureThatCodeIsUnique(BusinessRuleException exception, AccountJson accountJson) {
		Account exists = accountRepository.findByCodeLikeIgnoreCase(accountJson.getCode());
		if (exists != null && !exists.getId().equals(accountJson.getId())) {
			exception.addMessage(AccountMessageCode.ACCOUNT_CODE_NOT_AVAILABLE);
		}
	}

	private void ensureThatNameIsNotBlank(BusinessRuleException exception, AccountJson accountJson) {
		if (StringUtils.isEmpty(accountJson.getName())) {
			exception.addMessage(AccountMessageCode.ACCOUNT_NAME_IS_BLANK);
		} else {
			ensureThatNameIsUnique(exception, accountJson);
		}
	}

	private void ensureThatNameIsUnique(BusinessRuleException exception, AccountJson accountJson) {
		Account exists = accountRepository.findByNameLikeIgnoreCase(accountJson.getName());
		if (exists != null && !exists.getId().equals(accountJson.getId())) {
			exception.addMessage(AccountMessageCode.ACCOUNT_NAME_NOT_AVAILABLE);
		}
	}

	private void ensureThatExists(Integer accountId) {
		new BusinessRuleException().addMessage(() -> accountRepository.findOne(accountId) == null,
				AccountMessageCode.ACCOUNT_DOES_NOT_EXISTS).throwException();
	}

}
