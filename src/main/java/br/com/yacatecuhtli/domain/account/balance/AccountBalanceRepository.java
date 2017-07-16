package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import br.com.yacatecuhtli.domain.account.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends EntityRepository<AccountBalance> {

    AccountBalance findByAccountAndYearAndMonth(Account account, Integer year, Integer month);

}
