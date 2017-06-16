package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BudgetGroupService extends AbstractCrudService<BudgetGroupJson, BudgetGroup, BudgetGroupConverter, BudgetGroupRepository> {

    @Autowired
    public BudgetGroupService(BudgetGroupConverter jsonConverter, BudgetGroupRepository entityRepository) {
        super(jsonConverter, entityRepository);
    }

    @Override
    protected void validate(BudgetGroupJson jsonRepresentation) {
        ensureThatNameIsNotBlank(jsonRepresentation);
    }

    private void ensureThatNameIsNotBlank(BudgetGroupJson budgetGroupJson) {
        if (StringUtils.isEmpty(budgetGroupJson.getName())) {
            new BusinessRuleException().addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(budgetGroupJson);
        }
    }

    private void ensureThatNameIsUnique(BudgetGroupJson budgetGroupJson) {
        BudgetGroup exists = entityRepository.findByNameLikeIgnoreCase(budgetGroupJson.getName());
        if (exists != null && !exists.getId().equals(budgetGroupJson.getId())) {
            new BusinessRuleException().addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_NOT_AVAILABLE).throwException();
        }
    }

    @Override
    protected void validateDelete(Integer entityId) {
        ensureThatExists(entityId);
    }

    private void ensureThatExists(Integer budgetGroupId) {
        new BusinessRuleException()
                .addMessage(() -> entityRepository.findOne(budgetGroupId) == null, BudgetGroupMessageCode.BUDGET_GROUP_DOES_NOT_EXISTS)
                .throwException();
    }

}
