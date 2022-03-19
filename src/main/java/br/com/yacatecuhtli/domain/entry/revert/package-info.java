/**
 * @api {delete} /entries/:entryId/reverse Revert an entry
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName RevertEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiParam {Number} entryId
 * @apiSuccess {Object} attributes
 * @apiUse ResponseEntryJson
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.entry.revert;
