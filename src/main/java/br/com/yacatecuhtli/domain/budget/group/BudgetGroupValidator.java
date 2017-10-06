package br.com.yacatecuhtli.domain.budget.group;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BudgetGroupValidator extends CrudValidator<BudgetGroupJson> {

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Override
    public void executeValidation(BudgetGroupJson jsonRepresentation) throws BusinessRuleException {
        ensureThatNameIsNotBlank(jsonRepresentation);
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(BudgetGroupJson budgetGroupJson) {
        if (StringUtils.isEmpty(budgetGroupJson.getName())) {
            new BusinessRuleException().addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(budgetGroupJson);
        }
    }

    private void ensureThatNameIsUnique(BudgetGroupJson budgetGroupJson) {
        BudgetGroup exists = budgetGroupRepository.findByNameLikeIgnoreCase(budgetGroupJson.getName());
        if (exists != null && !exists.getId().equals(budgetGroupJson.getId())) {
            new BusinessRuleException().addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_NOT_AVAILABLE).throwException();
        }
    }

    private void ensureThatExists(Integer budgetGroupId) {
        new BusinessRuleException()
                .addMessage(() -> budgetGroupRepository.findOne(budgetGroupId) == null, BudgetGroupMessageCode.BUDGET_GROUP_DOES_NOT_EXISTS)
                .throwException();
    }

}
