package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.exception.BusinessRuleException;
import br.com.yacatecuhtli.core.validator.CrudValidator;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroup;
import br.com.yacatecuhtli.domain.budget.group.BudgetGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BudgetCategoryValidator extends CrudValidator<BudgetCategoryJson> {

    @Autowired
    private BudgetCategoryRepository budgetCategoryRepository;

    @Autowired
    private BudgetGroupRepository budgetGroupRepository;

    @Override
    public void executeValidation(BudgetCategoryJson jsonRepresentation) throws BusinessRuleException {
        ensureThatNameIsNotBlank(jsonRepresentation);
        ensureGroupIsNotBlank(jsonRepresentation);
    }

    @Override
    public void exists(Integer entityId) throws BusinessRuleException {
        ensureThatExists(entityId);
    }

    private void ensureThatNameIsNotBlank(BudgetCategoryJson budgetCategoryJson) {
        if (StringUtils.isEmpty(budgetCategoryJson.getName())) {
            new BusinessRuleException().addMessage(BudgetCategoryMessageCode.BUDGET_CATEGORY_NAME_IS_BLANK).throwException();
        } else {
            ensureThatNameIsUnique(budgetCategoryJson);
        }
    }

    private void ensureThatNameIsUnique(BudgetCategoryJson budgetCategoryJson) {
        BudgetCategory exists = budgetCategoryRepository.findByNameLikeIgnoreCase(budgetCategoryJson.getName());
        if (exists != null && !exists.getId().equals(budgetCategoryJson.getId())) {
            new BusinessRuleException().addMessage(BudgetCategoryMessageCode.BUDGET_CATEGORY_NAME_NOT_AVAILABLE).throwException();
        }
    }

    private void ensureGroupIsNotBlank(BudgetCategoryJson budgetCategoryJson) {
        if (budgetCategoryJson.getGroup() == null || budgetCategoryJson.getGroup().getId() == null) {
            new BusinessRuleException().addMessage(BudgetCategoryMessageCode.BUDGET_CATEGORY_GROUP_IS_BLANK).throwException();
        } else if (budgetCategoryJson.getGroup().getId() != null) {
            ensureGroupExists(budgetCategoryJson);
        }
    }

    private void ensureGroupExists(BudgetCategoryJson budgetCategoryJson) {
        BudgetGroup exists = budgetGroupRepository.findOne(budgetCategoryJson.getGroup().getId());
        if (exists == null) {
            new BusinessRuleException().addMessage(BudgetCategoryMessageCode.BUDGET_CATEGORY_GROUP_NOT_AVAILABLE).throwException();
        }
    }

    private void ensureThatExists(Integer budgetCategoryId) {
        new BusinessRuleException()
                .addMessage(() -> budgetCategoryRepository.findOne(budgetCategoryId) == null, BudgetCategoryMessageCode.BUDGET_CATEGORY_DOES_NOT_EXISTS)
                .throwException();
    }
}
