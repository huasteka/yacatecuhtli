package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountService extends AbstractCrudService<AccountJson, Account, AccountConverter, AccountRepository> {

    @Autowired
    public AccountService(AccountConverter jsonConverter, AccountRepository entityRepository) {
        super(jsonConverter, entityRepository);
    }

    @Override
    protected void validate(AccountJson jsonRepresentation) {
        ensureThatNameIsNotBlank(jsonRepresentation);
    }

    private void ensureThatNameIsNotBlank(AccountJson accountJson) {
        if (StringUtils.isEmpty(accountJson.getName())) {
            new BusinessRuleException().addMessage(AccountMessageCode.ACCOUNT_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(accountJson);
        }
    }

    private void ensureThatNameIsUnique(AccountJson accountJson) {
        Account exists = entityRepository.findByNameLikeIgnoreCase(accountJson.getName());
        if (exists != null && !exists.getId().equals(accountJson.getId())) {
            new BusinessRuleException().addMessage(AccountMessageCode.ACCOUNT_NAME_NOT_AVAILABLE).throwException();
        }
    }

    @Override
    protected void validateDelete(Integer entityId) {
        ensureThatExists(entityId);
    }

    private void ensureThatExists(Integer accountId) {
        new BusinessRuleException()
                .addMessage(() -> entityRepository.findOne(accountId) == null, AccountMessageCode.ACCOUNT_DOES_NOT_EXISTS)
                .throwException();
    }


}
