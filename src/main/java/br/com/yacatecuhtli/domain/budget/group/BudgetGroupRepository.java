package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetGroupRepository extends EntityRepository<BudgetGroup> {

    BudgetGroup findByNameLikeIgnoreCase(String name);

}
