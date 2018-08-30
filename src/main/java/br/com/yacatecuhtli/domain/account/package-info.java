/**
 * @api {get} /accounts Fetch a collection of accounts
 * @apiVersion 1.0.0
 * @apiGroup Account
 * @apiName GetAccounts
 * @apiHeader {String} Authorization User generated JWT token
 *
 * @apiSuccess {Object[]} accounts
 * @apiSuccess {Number}   accounts.id
 * @apiSuccess {String}   accounts.code
 * @apiSuccess {String}   accounts.name
 *
 * @apiSuccessExample {json} Success-Response:
 *  HTTP/1.1 200 OK
 *  {
 *    "attributes": [
 *      {
 *        "id": "1"
 *        "code": "A001"
 *        "name": "Example"
 *      },
 *      {
 *        "id": "2"
 *        "code": "A002"
 *        "name": "Example 2"
 *      },
 *    ]
 *  }
 *
 * @apiError Y001 User is not authenticated
 *
 * @apiErrorExample {json} Error-Response:
 *  HTTP/1.1 401 Unauthorized
 *  {
 *    "errors": [
 *      "messageKey": "Y001",
 *      "message": "User is not authenticated"
 *    ]
 *  }
 */
package br.com.yacatecuhtli.domain.account;
