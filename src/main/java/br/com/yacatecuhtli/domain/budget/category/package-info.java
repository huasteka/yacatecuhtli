/**
 * @apiDefine RequestBudgetCategoryJson
 * @apiBody {String} name
 * @apiBody {Object} group
 * @apiBody {Number} group.id
 */

/**
 * @apiDefine ResponseBudgetCategoryJson
 * @apiSuccess {Number} attributes.id
 * @apiSuccess {String} attributes.name
 * @apiSuccess {Object} attributes.group
 * @apiSuccess {Number} attributes.group.id
 * @apiSuccess {String} attributes.group.name
 */

/**
 * @api {get} /budget-categories Fetch a list of budget categories
 * @apiVersion 1.0.0
 * @apiGroup BudgetCategory
 * @apiName GetBudgetCategoryList
 * @apiHeader {String} Authorization User generated JWT token
 * @apiUse RequestPagination
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponseBudgetCategoryJson
 * @apiUse ResponseMetadataJson
 */

/**
 * @api {get} /budget-categories/:budgetCategoryId Fetch a single budget category
 * @apiVersion 1.0.0
 * @apiGroup BudgetCategory
 * @apiName GetBudgetCategory
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetCategoryId
 * @apiSuccess {Object} attributes
 * @apiUse ResponseBudgetCategoryJson
 */

/**
 * @api {post} /budget-categories Create a budget category
 * @apiVersion 1.0.0
 * @apiGroup BudgetCategory
 * @apiName CreateBudgetCategory
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestBudgetCategoryJson
 * @apiSuccess {Object} attributes
 * @apiUse ResponseBudgetCategoryJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {put} /budget-categories/:budgetCategoryId Update a budget category
 * @apiVersion 1.0.0
 * @apiGroup BudgetCategory
 * @apiName UpdateBudgetCategory
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetCategoryId
 * @apiBody {Number} id
 * @apiUse RequestBudgetCategoryJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {delete} /budget-categories/:budgetCategoryId Delete a budget category
 * @apiVersion 1.0.0
 * @apiGroup BudgetCategory
 * @apiName DeleteBudgetCategory
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} budgetCategoryId
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.budget.category;
