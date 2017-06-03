package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService extends AbstractService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountConverter accountConverter;

    @Transactional
    public AccountJson save(AccountJson accountJson) {
        ensureThatNameIsNotBlank(accountJson);
        return accountRepository.save(accountConverter.convert(accountJson)).toJson();
    }

    @Transactional
    public void update(Integer accountId, AccountJson accountJson) {
        ensureThatNameIsNotBlank(accountJson);
        Account account = accountRepository.findOne(accountId);
        accountConverter.update(accountJson, account);
        accountRepository.save(account);
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

    @Transactional
    public void destroy(Integer accountId) {
        ensureThatExists(accountId);
        accountRepository.delete(accountId);
    }

    private void ensureThatExists(Integer accountId) {
        new BusinessRuleException()
                .addMessage(() -> accountRepository.findOne(accountId) == null, AccountMessageCode.ACCOUNT_DOES_NOT_EXISTS)
                .throwException();
    }

    public AccountJson findOne(Integer accountId) {
        return Optional.ofNullable(accountRepository.findOne(accountId)).map(Account::toJson).orElse(null);
    }

    public List<AccountJson> findAll() {
        return Optional.ofNullable(accountRepository.findAll())
                .map(list -> list.stream().map(Account::toJson).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
