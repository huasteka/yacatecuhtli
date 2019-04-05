package br.com.yacatecuhtli.domain.budget.category;

import br.com.yacatecuhtli.core.exception.ErrorMessageCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BudgetCategoryMessageCode implements ErrorMessageCode {

    BUDGET_CATEGORY_NAME_IS_BLANK("budget-category.name.error.is-blank"),
    BUDGET_CATEGORY_NAME_NOT_AVAILABLE("budget-category.name.error.not-available"),
    BUDGET_CATEGORY_GROUP_IS_BLANK("budget-category.group.error.is-blank"),
    BUDGET_CATEGORY_GROUP_NOT_AVAILABLE("budget-category.group.error.not-available"),
    BUDGET_CATEGORY_DOES_NOT_EXISTS("budget-category.error.not-exists");

    @Getter
    private String messageKey;

}

