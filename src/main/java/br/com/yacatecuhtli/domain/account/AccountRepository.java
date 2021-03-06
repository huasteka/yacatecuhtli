package br.com.yacatecuhtli.domain.account;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends EntityRepository<Account> {

    Account findByCodeLikeIgnoreCase(String code);

    Account findByNameLikeIgnoreCase(String name);

}
