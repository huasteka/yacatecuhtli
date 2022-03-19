/**
 * @apiDefine RequestEntryJson
 * @apiBody {String} [code]
 * @apiBody {Number} grossValue
 * @apiBody {Number} [addition]
 * @apiBody {Number} [discount]
 * @apiBody {Number} [netValue]
 * @apiBody {String} [description]
 * @apiBody {Object} paymentType
 * @apiBody {Number} paymentType.id
 * @apiBody {Object} account
 * @apiBody {Number} account.id
 * @apiBody {Object} [category]
 * @apiBody {Number} [category.id]
 */

/**
 * @apiDefine ResponseEntryJson
 * @apiSuccess {Number} attributes.id
 * @apiSuccess {String="DEPOSIT","WITHDRAW"} attributes.type
 * @apiSuccess {String} attributes.code
 * @apiSuccess {Number} attributes.grossValue
 * @apiSuccess {Number} attributes.addition
 * @apiSuccess {Number} attributes.discount
 * @apiSuccess {Number} attributes.netValue
 * @apiSuccess {String} attributes.description
 * @apiSuccess {Object} attributes.account
 * @apiSuccess {Number} attributes.account.id
 * @apiSuccess {String} attributes.account.code
 * @apiSuccess {String} attributes.account.name
 * @apiSuccess {Object} attributes.paymentType
 * @apiSuccess {Number} attributes.paymentType.id
 * @apiSuccess {String} attributes.paymentType.name
 * @apiSuccess {Object} attributes.paymentType.terms
 * @apiSuccess {Boolean} attributes.paymentType.terms.stagedPayment
 * @apiSuccess {Number} attributes.paymentType.terms.firstInstallmentTerm
 * @apiSuccess {Number} attributes.paymentType.terms.installmentQuantity
 * @apiSuccess {Number} attributes.paymentType.terms.installmentTerm
 * @apiSuccess {Number} attributes.paymentType.terms.tax
 * @apiSuccess {Object} attributes.paymentType.paymentAccount
 * @apiSuccess {Number} attributes.paymentType.paymentAccount.id
 * @apiSuccess {String} attributes.paymentType.paymentAccount.name
 * @apiSuccess {String} attributes.paymentType.paymentAccount.code
 * @apiSuccess {Object} attributes.category
 * @apiSuccess {Number} attributes.category.id
 * @apiSuccess {String} attributes.category.name
 * @apiSuccess {Object} attributes.category.group
 * @apiSuccess {Number} attributes.category.group.id
 * @apiSuccess {String} attributes.category.group.name
 * @apiSuccess {Date} attributes.issuedAt
 * @apiSuccess {Date} attributes.executedAt
 * @apiSuccess {Date} attributes.reversedAt
 */

/**
 * @api {get} /entries/:entryId Fetch a single entry
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName GetEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiParam {Number} entryId
 * @apiSuccess {Object} attributes
 * @apiUse ResponseEntryJson
 */

/**
 * @api {get} /entries/accounts/:accountId Fetch a list of entries by account
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName GetEntriesByAccount
 * @apiHeader {String} Authorization Generated JWT token
 * @apiParam {Number} entryId
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponseEntryJson
 * @apiUse ResponseMetadataJson
 */

/**
 * @api {get} /entries/search-code/:entryCode Fetch a list of entries by code
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName GetEntriesByCode
 * @apiHeader {String} Authorization Generated JWT token
 * @apiParam {String} entryCode
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponseEntryJson
 */

/**
 * @api {post} /entries/deposit Deposit into account
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName PostEntryDeposit
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestEntryJson
 * @apiSuccess {Object} attributes
 * @apiUse ResponseEntryJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {post} /entries/withdraw Withdraw from account
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName PostEntryWithdraw
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestEntryJson
 * @apiSuccess {Object} attributes
 * @apiUse ResponseEntryJson
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.entry;
