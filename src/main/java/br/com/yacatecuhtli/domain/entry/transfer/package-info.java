/**
 * @apiDefine ResponseTransferSourceEntryJson
 * @apiSuccess {Object} attributes.source
 * @apiSuccess {Number} attributes.source.id
 * @apiSuccess {String="DEPOSIT","WITHDRAW"} attributes.source.type
 * @apiSuccess {String} attributes.source.code
 * @apiSuccess {Number} attributes.source.grossValue
 * @apiSuccess {Number} attributes.source.addition
 * @apiSuccess {Number} attributes.source.discount
 * @apiSuccess {Number} attributes.source.netValue
 * @apiSuccess {String} attributes.source.description
 * @apiSuccess {Object} attributes.source.account
 * @apiSuccess {Number} attributes.source.account.id
 * @apiSuccess {String} attributes.source.account.code
 * @apiSuccess {String} attributes.source.account.name
 * @apiSuccess {Object} attributes.source.paymentType
 * @apiSuccess {Number} attributes.source.paymentType.id
 * @apiSuccess {String} attributes.source.paymentType.name
 * @apiSuccess {Object} attributes.source.paymentType.terms
 * @apiSuccess {Boolean} attributes.source.paymentType.terms.stagedPayment
 * @apiSuccess {Number} attributes.source.paymentType.terms.firstInstallmentTerm
 * @apiSuccess {Number} attributes.source.paymentType.terms.installmentQuantity
 * @apiSuccess {Number} attributes.source.paymentType.terms.installmentTerm
 * @apiSuccess {Number} attributes.source.paymentType.terms.tax
 * @apiSuccess {Object} attributes.source.paymentType.paymentAccount
 * @apiSuccess {Number} attributes.source.paymentType.paymentAccount.id
 * @apiSuccess {String} attributes.source.paymentType.paymentAccount.name
 * @apiSuccess {String} attributes.source.paymentType.paymentAccount.code
 * @apiSuccess {Object} attributes.source.category
 * @apiSuccess {Number} attributes.source.category.id
 * @apiSuccess {String} attributes.source.category.name
 * @apiSuccess {Object} attributes.source.category.group
 * @apiSuccess {Number} attributes.source.category.group.id
 * @apiSuccess {String} attributes.source.category.group.name
 * @apiSuccess {Date} attributes.source.issuedAt
 * @apiSuccess {Date} attributes.source.executedAt
 * @apiSuccess {Date} attributes.source.reversedAt
 */

/**
 * @apiDefine ResponseTransferTargetEntryJson
 * @apiSuccess {Object} attributes.target
 * @apiSuccess {Number} attributes.target.id
 * @apiSuccess {String="DEPOSIT","WITHDRAW"} attributes.target.type
 * @apiSuccess {String} attributes.target.code
 * @apiSuccess {Number} attributes.target.grossValue
 * @apiSuccess {Number} attributes.target.addition
 * @apiSuccess {Number} attributes.target.discount
 * @apiSuccess {Number} attributes.target.netValue
 * @apiSuccess {String} attributes.target.description
 * @apiSuccess {Object} attributes.target.account
 * @apiSuccess {Number} attributes.target.account.id
 * @apiSuccess {String} attributes.target.account.code
 * @apiSuccess {String} attributes.target.account.name
 * @apiSuccess {Object} attributes.target.paymentType
 * @apiSuccess {Number} attributes.target.paymentType.id
 * @apiSuccess {String} attributes.target.paymentType.name
 * @apiSuccess {Object} attributes.target.paymentType.terms
 * @apiSuccess {Boolean} attributes.target.paymentType.terms.stagedPayment
 * @apiSuccess {Number} attributes.target.paymentType.terms.firstInstallmentTerm
 * @apiSuccess {Number} attributes.target.paymentType.terms.installmentQuantity
 * @apiSuccess {Number} attributes.target.paymentType.terms.installmentTerm
 * @apiSuccess {Number} attributes.target.paymentType.terms.tax
 * @apiSuccess {Object} attributes.target.paymentType.paymentAccount
 * @apiSuccess {Number} attributes.target.paymentType.paymentAccount.id
 * @apiSuccess {String} attributes.target.paymentType.paymentAccount.name
 * @apiSuccess {String} attributes.target.paymentType.paymentAccount.code
 * @apiSuccess {Object} attributes.target.category
 * @apiSuccess {Number} attributes.target.category.id
 * @apiSuccess {String} attributes.target.category.name
 * @apiSuccess {Object} attributes.target.category.group
 * @apiSuccess {Number} attributes.target.category.group.id
 * @apiSuccess {String} attributes.target.category.group.name
 * @apiSuccess {Date} attributes.target.issuedAt
 * @apiSuccess {Date} attributes.target.executedAt
 * @apiSuccess {Date} attributes.target.reversedAt
 */

/**
 * @apiDefine ResponseTransferJson
 * @apiSuccess {Number} attributes.id
 * @apiUse ResponseTransferSourceEntryJson
 * @apiUse ResponseTransferTargetEntryJson
 * @apiSuccess {Date} attributes.transferredAt
 */

/**
 * @api {post} /transfers Transfer an entry
 * @apiVersion 1.0.0
 * @apiGroup Entry
 * @apiName TransferEntry
 * @apiHeader {String} Authorization Generated JWT token
 * @apiBody {Number} sourceAccountId
 * @apiBody {Number} targetAccountId
 * @apiBody {Number} paymentTypeId
 * @apiBody {Number} amount
 * @apiUse ResponseTransferJson
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.entry.transfer;
