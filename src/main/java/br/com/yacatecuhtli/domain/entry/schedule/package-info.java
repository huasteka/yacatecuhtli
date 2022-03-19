/**
 * @apiDefine RequestScheduleJson
 * @apiBody {Object} entry
 * @apiBody {String="DEPOSIT","WITHDRAW"} entry.type
 * @apiBody {Number} entry.grossValue
 * @apiBody {Object} entry.paymentType
 * @apiBody {Number} entry.paymentType.id
 * @apiBody {Object} entry.account
 * @apiBody {Number} entry.account.id
 * @apiBody {Object} category
 * @apiBody {Number} category.id
 * @apiBody {Date} executeAt
 */

/**
 * @apiDefine ResponseScheduleJson
 * @apiResponse {Object} attributes
 * @apiResponse {Number} attributes.id
 * @apiResponse {Object} attributes.entry
 * @apiSuccess {Number} attributes.entry.id
 * @apiSuccess {String} attributes.entry.type
 * @apiSuccess {String} attributes.entry.code
 * @apiSuccess {Number} attributes.entry.grossValue
 * @apiSuccess {Number} attributes.entry.addition
 * @apiSuccess {Number} attributes.entry.discount
 * @apiSuccess {Number} attributes.entry.netValue
 * @apiSuccess {String} attributes.entry.description
 * @apiSuccess {Object} attributes.entry.account
 * @apiSuccess {Number} attributes.entry.account.id
 * @apiSuccess {String} attributes.entry.account.code
 * @apiSuccess {String} attributes.entry.account.name
 * @apiSuccess {Object} attributes.entry.paymentType
 * @apiSuccess {Number} attributes.entry.paymentType.id
 * @apiSuccess {String} attributes.entry.paymentType.name
 * @apiSuccess {Object} attributes.entry.paymentType.terms
 * @apiSuccess {Boolean} attributes.entry.paymentType.terms.stagedPayment
 * @apiSuccess {Number} attributes.entry.paymentType.terms.firstInstallmentTerm
 * @apiSuccess {Number} attributes.entry.paymentType.terms.installmentQuantity
 * @apiSuccess {Number} attributes.entry.paymentType.terms.installmentTerm
 * @apiSuccess {Number} attributes.entry.paymentType.terms.tax
 * @apiSuccess {Object} attributes.entry.paymentType.paymentAccount
 * @apiSuccess {Number} attributes.entry.paymentType.paymentAccount.id
 * @apiSuccess {String} attributes.entry.paymentType.paymentAccount.name
 * @apiSuccess {String} attributes.entry.paymentType.paymentAccount.code
 * @apiSuccess {Object} attributes.entry.category
 * @apiSuccess {Number} attributes.entry.category.id
 * @apiSuccess {String} attributes.entry.category.name
 * @apiSuccess {Object} attributes.entry.category.group
 * @apiSuccess {Number} attributes.entry.category.group.id
 * @apiSuccess {String} attributes.entry.category.group.name
 * @apiSuccess {Date} attributes.entry.issuedAt
 * @apiSuccess {Date} attributes.entry.executedAt
 * @apiSuccess {Date} attributes.entry.reversedAt
 * @apiResponse {Object} attributes.category
 * @apiResponse {Number} attributes.category.id
 * @apiResponse {String} attributes.category.name
 * @apiResponse {Object} attributes.category.group
 * @apiResponse {Number} attributes.category.group.id
 * @apiResponse {String} attributes.category.group.name
 * @apiResponse {Object} attributes.executeAt
 */

/**
 * @api {post} /schedules/deposit Schedule an deposit
 * @apiVersion 1.0.0
 * @apiGroup Schedule
 * @apiName ScheduledDepositEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestScheduleJson
 * @apiUse ResponseScheduleJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {post} /schedules/withdraw Schedule an withdraw
 * @apiVersion 1.0.0
 * @apiGroup Schedule
 * @apiName ScheduledWithdrawEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestScheduleJson
 * @apiUse ResponseScheduleJson
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.entry.schedule;
