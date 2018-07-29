package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.json.JsonConverter;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter extends JsonConverter<AccountJson, Account> {

    @Override
    public Account convert(AccountJson source) {
        Account account = new Account();
        update(source, account);
        return account;
    }

    @Override
    public void update(AccountJson source, Account target) {
        target.setName(source.getName());
        target.setCode(source.getCode());
    }

}
