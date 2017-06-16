package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.entity.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetCategoryRepository extends EntityRepository<BudgetCategory> {

    BudgetCategory findByNameLikeIgnoreCase(String name);

}
