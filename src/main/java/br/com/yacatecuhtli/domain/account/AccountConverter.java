package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.json.JsonToEntityConverter;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter extends JsonToEntityConverter<AccountJson, Account> {

    @Override
    public Account convert(AccountJson source) {
        Account account = new Account();
        update(source, account);
        return account;
    }

    @Override
    public void update(AccountJson source, Account target) {

    }

}
