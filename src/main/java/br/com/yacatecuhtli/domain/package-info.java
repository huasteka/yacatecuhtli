/**
 * @apiDefine RequestPagination
 * @apiQuery {Number} [pageSize]
 * @apiQuery {Number} [currentPage]
 */

/**
 * @apiDefine ResponseMetadataJson
 * @apiSuccess {Object} meta
 * @apiSuccess {Number} meta.pageSize
 * @apiSuccess {Number} meta.currentPage
 * @apiSuccess {Number} meta.totalPages
 * @apiSuccess {Number} meta.totalItems
 */

/**
 * @apiDefine ResponseErrorMessageJson
 * @apiError {Object} errors
 * @apiError {Number} errors.status
 * @apiError {String} errors.messageKey
 * @apiError {String} errors.message
 */
package br.com.yacatecuhtli.domain;