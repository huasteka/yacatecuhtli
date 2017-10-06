package br.com.yacatecuhtli.domain.account;


import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AccountValidator extends CrudValidator<AccountJson> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void executeValidation(AccountJson accountJson) throws BusinessRuleException {
        ensureThatNameIsNotBlank(accountJson);
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(AccountJson accountJson) {
        if (StringUtils.isEmpty(accountJson.getName())) {
            new BusinessRuleException().addMessage(AccountMessageCode.ACCOUNT_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(accountJson);
        }
    }

    private void ensureThatNameIsUnique(AccountJson accountJson) {
        Account exists = accountRepository.findByNameLikeIgnoreCase(accountJson.getName());
        if (exists != null && !exists.getId().equals(accountJson.getId())) {
            new BusinessRuleException().addMessage(AccountMessageCode.ACCOUNT_NAME_NOT_AVAILABLE).throwException();
        }
    }

    private void ensureThatExists(Integer accountId) {
        new BusinessRuleException()
                .addMessage(() -> accountRepository.findOne(accountId) == null, AccountMessageCode.ACCOUNT_DOES_NOT_EXISTS)
                .throwException();
    }

}
