/**
 * @apiDefine ResponseBudgetGroupJson
 * @apiSuccess {Number} attributes.id
 * @apiSuccess {String} attributes.name
 */

/**
 * @api {get} /budget-groups Fetch a list of budget groups
 * @apiVersion 1.0.0
 * @apiGroup BudgetGroup
 * @apiName GetBudgetGroupList
 * @apiHeader {String} Authorization User generated JWT token
 * @apiUse RequestPagination
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponseBudgetGroupJson
 * @apiUse ResponseMetadataJson
 */

/**
 * @api {get} /budget-groups/:budgetGroupId Fetch a single budget group
 * @apiVersion 1.0.0
 * @apiGroup BudgetGroup
 * @apiName GetBudgetGroup
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetGroupId
 * @apiSuccess {Object} attributes
 * @apiUse ResponseBudgetGroupJson
 */

/**
 * @api {post} /budget-groups Create a budget group
 * @apiVersion 1.0.0
 * @apiGroup BudgetGroup
 * @apiName CreateBudgetGroup
 * @apiHeader {String} Authorization Generated JWT token
 * @apiBody {String} name
 * @apiSuccess {Object} attributes
 * @apiUse ResponseBudgetGroupJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {put} /budget-groups/:budgetGroupId Update a budget group
 * @apiVersion 1.0.0
 * @apiGroup BudgetGroup
 * @apiName UpdateBudgetGroup
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetGroupId
 * @apiBody {Number} id
 * @apiBody {String} name
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {delete} /budget-groups/:budgetGroupId Delete a budget group
 * @apiVersion 1.0.0
 * @apiGroup BudgetGroup
 * @apiName DeleteBudgetGroup
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetGroupId
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.budget.group;
