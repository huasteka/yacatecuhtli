package br.com.yacatecuhtli.domain.account.balance;

import br.com.yacatecuhtli.core.SystemTime;
import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractService;
import br.com.yacatecuhtli.domain.account.Account;
import br.com.yacatecuhtli.domain.entry.Entry;
import br.com.yacatecuhtli.domain.entry.EntryType;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountBalanceService extends AbstractService {

    @Autowired
    protected AccountBalanceRepository accountBalanceRepository;

    @Transactional
    public void performOperation(Entry entry) {
        AccountBalance accountBalance = createAccountBalanceIfNotExists(entry);
        if (EntryType.DEPOSIT.equals(entry.getType())) {
            accountBalance.setBalance(Optional.ofNullable(accountBalance.getBalance()).orElse(BigDecimal.ZERO).add(entry.getTotal()));
        } else if (EntryType.WITHDRAW.equals(entry.getType())) {
            accountBalance.setBalance(Optional.ofNullable(accountBalance.getBalance()).orElse(BigDecimal.ZERO).subtract(entry.getTotal()));
        } else {
            throw new BusinessRuleException(AccountBalanceMessageCode.ACCOUNT_BALANCE_OPERATION_TYPE_IS_BLANK);
        }
        accountBalanceRepository.save(accountBalance);
    }

    private AccountBalance createAccountBalanceIfNotExists(Entry entry) {
        DateTime now = new DateTime(SystemTime.INSTANCE.now());
        AccountBalance accountBalance = accountBalanceRepository.findByAccountAndYearAndMonth(entry.getAccount(), now.getYear(), now.getMonthOfYear());
        if (accountBalance == null) {
            accountBalance = new AccountBalance();
            accountBalance.setAccount(entry.getAccount());
            accountBalance.setYear(now.getYear());
            accountBalance.setMonth(now.getMonthOfYear());
        }
        return accountBalance;
    }

    public AccountBalance findByAccount(Account account, Integer year, Integer month) {
        return accountBalanceRepository.findByAccountAndYearAndMonth(account, year, month);
    }

}
