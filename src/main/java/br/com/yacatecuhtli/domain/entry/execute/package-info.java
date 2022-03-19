/**
 * @api {post} /entries/:entryId/execute Execute an entry
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName ExecuteEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiParam {Number} entryId
 * @apiBody {Number} entryId
 * @apiBody {Number} budgetCategoryId
 * @apiBody {Number} addition
 * @apiBody {Number} discount
 * @apiBody {Date} executeAt
 * @apiSuccess {Object} attributes
 * @apiUse ResponseEntryJson
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.entry.execute;
