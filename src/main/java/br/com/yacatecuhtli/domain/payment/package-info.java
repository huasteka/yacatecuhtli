/**
 * @apiDefine RequestPaymentTypeJson
 * @apiBody {String} name
 * @apiBody {Object} [terms]
 * @apiBody {Boolean} [terms.stagedPayment]
 * @apiBody {Number} [terms.firstInstallmentTerm]
 * @apiBody {Number} [terms.installmentQuantity]
 * @apiBody {Number} [terms.installmentTerm]
 * @apiBody {Number} [terms.tax]
 * @apiBody {Object} paymentAccount
 * @apiBody {Number} paymentAccount.id
 */

/**
 * @apiDefine ResponsePaymentTypeJson
 * @apiSuccess {Number} attributes.id
 * @apiSuccess {String} attributes.name
 * @apiSuccess {Object} attributes.terms
 * @apiSuccess {Boolean} attributes.terms.stagedPayment
 * @apiSuccess {Number} attributes.terms.firstInstallmentTerm
 * @apiSuccess {Number} attributes.terms.installmentQuantity
 * @apiSuccess {Number} attributes.terms.installmentTerm
 * @apiSuccess {Number} attributes.terms.tax
 * @apiSuccess {Object} attributes.paymentAccount
 * @apiSuccess {Number} attributes.paymentAccount.id
 * @apiSuccess {String} attributes.paymentAccount.name
 * @apiSuccess {String} attributes.paymentAccount.code
 */

/**
 * @api {get} /payment-types Fetch a list of payment types
 * @apiVersion 1.0.0
 * @apiGroup PaymentType
 * @apiName GetPaymentTypeList
 * @apiHeader {String} Authorization User generated JWT token
 * @apiUse RequestPagination
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponsePaymentTypeJson
 * @apiUse ResponseMetadataJson
 */

/**
 * @api {get} /payment-types/:paymentTypeId Fetch a single payment type
 * @apiVersion 1.0.0
 * @apiGroup PaymentType
 * @apiName GetPaymentType
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} paymentTypeId
 * @apiSuccess {Object} attributes
 * @apiUse ResponsePaymentTypeJson
 */

/**
 * @api {post} /payment-types Create a payment type
 * @apiVersion 1.0.0
 * @apiGroup PaymentType
 * @apiName CreatePaymentType
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestPaymentTypeJson
 * @apiSuccess {Object} attributes
 * @apiUse ResponsePaymentTypeJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {put} /payment-types/:paymentTypeId Update a payment type
 * @apiVersion 1.0.0
 * @apiGroup PaymentType
 * @apiName UpdatePaymentType
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} paymentTypeId
 * @apiBody {Number} id
 * @apiUse RequestPaymentTypeJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {delete} /payment-types/:paymentTypeId Delete a payment type
 * @apiVersion 1.0.0
 * @apiGroup PaymentType
 * @apiName DeletePaymentType
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} paymentTypeId
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.payment;
