package br.com.yacatecuhtli.domain.budget.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;

@Component
public class BudgetGroupValidator extends CrudValidator<BudgetGroupJson> {

    private BudgetGroupRepository budgetGroupRepository;

    @Autowired
    public BudgetGroupValidator(BudgetGroupRepository budgetGroupRepository) {
		this.budgetGroupRepository = budgetGroupRepository;
	}

	@Override
    public void executeValidation(BudgetGroupJson jsonRepresentation) throws BusinessRuleException {
        ensureThatNameIsNotBlank(jsonRepresentation);
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(BudgetGroupJson budgetGroupJson) {
        BusinessRuleException exception = new BusinessRuleException();
        if (StringUtils.isEmpty(budgetGroupJson.getName())) {
            exception.addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_IS_BLANK);
        } else {
            ensureThatNameIsUnique(exception, budgetGroupJson);
        }
        exception.throwException();
    }

    private void ensureThatNameIsUnique(BusinessRuleException exception, BudgetGroupJson budgetGroupJson) {
        BudgetGroup exists = budgetGroupRepository.findByNameLikeIgnoreCase(budgetGroupJson.getName());
        if (exists != null && !exists.getId().equals(budgetGroupJson.getId())) {
            exception.addMessage(BudgetGroupMessageCode.BUDGET_GROUP_NAME_NOT_AVAILABLE);
        }
    }

    private void ensureThatExists(Integer budgetGroupId) {
        new BusinessRuleException()
                .addMessage(() -> budgetGroupRepository.findOne(budgetGroupId) == null, BudgetGroupMessageCode.BUDGET_GROUP_DOES_NOT_EXISTS)
                .throwException();
    }

}
