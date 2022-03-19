/**
 * @apiDefine RequestAccountJson
 * @apiBody {String} name
 * @apiBody {String} code
 */

/**
 * @apiDefine ResponseAccountJson
 * @apiSuccess {Number} attributes.id
 * @apiSuccess {String} attributes.code
 * @apiSuccess {String} attributes.name
 */

/**
 * @api {get} /accounts Fetch a list of accounts
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName GetAccountList
 * @apiHeader {String} Authorization User generated JWT token
 * @apiUse RequestPagination
 * @apiSuccess {Object[]} attributes
 * @apiUse ResponseAccountJson
 * @apiUse ResponseMetadataJson
 */

/**
 * @api {get} /accounts/:accountId Fetch a single account
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName GetAccount
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} accountId
 * @apiSuccess {Object} attributes
 * @apiUse ResponseAccountJson
 */

/**
 * @api {post} /accounts Create account
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName CreateAccount
 * @apiHeader {String} Authorization Generated JWT token
 * @apiUse RequestAccountJson
 * @apiSuccess {Object} attributes
 * @apiUse ResponseAccountJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {put} /accounts/:accountId Update account
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName UpdateAccount
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} accountId
 * @apiBody {Number} id
 * @apiUse RequestAccountJson
 * @apiUse ResponseErrorMessageJson
 */

/**
 * @api {delete} /accounts/:accountId Delete account
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName DeleteAccount
 * @apiHeader {String} Authorization User generated JWT token
 * @apiParam {Number} accountId
 * @apiUse ResponseErrorMessageJson
 */
package br.com.yacatecuhtli.domain.account;
