/**
 * @fileoverview gRPC-Web generated client stub for survey
 * @enhanceable
 * @public
 */

// Code generated by protoc-gen-grpc-web. DO NOT EDIT.
// versions:
// 	protoc-gen-grpc-web v1.5.0
// 	protoc              v5.26.1
// source: survey.proto


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.survey = require('./survey_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.survey.SurveyServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.survey.SurveyServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname.replace(/\/+$/, '');

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.survey.Survey,
 *   !proto.survey.Survey>}
 */
const methodDescriptor_SurveyService_CreateSurvey = new grpc.web.MethodDescriptor(
  '/survey.SurveyService/CreateSurvey',
  grpc.web.MethodType.UNARY,
  proto.survey.Survey,
  proto.survey.Survey,
  /**
   * @param {!proto.survey.Survey} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.survey.Survey.deserializeBinary
);


/**
 * @param {!proto.survey.Survey} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.survey.Survey)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Survey>|undefined}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServiceClient.prototype.createSurvey =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/survey.SurveyService/CreateSurvey',
      request,
      metadata || {},
      methodDescriptor_SurveyService_CreateSurvey,
      callback);
};


/**
 * @param {!proto.survey.Survey} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.survey.Survey>}
 *     Promise that resolves to the response
 */
proto.survey.SurveyServicePromiseClient.prototype.createSurvey =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/survey.SurveyService/CreateSurvey',
      request,
      metadata || {},
      methodDescriptor_SurveyService_CreateSurvey);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.survey.ListData,
 *   !proto.survey.Survey>}
 */
const methodDescriptor_SurveyService_ListSurveys = new grpc.web.MethodDescriptor(
  '/survey.SurveyService/ListSurveys',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.survey.ListData,
  proto.survey.Survey,
  /**
   * @param {!proto.survey.ListData} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.survey.Survey.deserializeBinary
);


/**
 * @param {!proto.survey.ListData} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Survey>}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServiceClient.prototype.listSurveys =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/survey.SurveyService/ListSurveys',
      request,
      metadata || {},
      methodDescriptor_SurveyService_ListSurveys);
};


/**
 * @param {!proto.survey.ListData} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Survey>}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServicePromiseClient.prototype.listSurveys =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/survey.SurveyService/ListSurveys',
      request,
      metadata || {},
      methodDescriptor_SurveyService_ListSurveys);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.survey.Vote,
 *   !proto.survey.Vote>}
 */
const methodDescriptor_SurveyService_SurveyVote = new grpc.web.MethodDescriptor(
  '/survey.SurveyService/SurveyVote',
  grpc.web.MethodType.UNARY,
  proto.survey.Vote,
  proto.survey.Vote,
  /**
   * @param {!proto.survey.Vote} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.survey.Vote.deserializeBinary
);


/**
 * @param {!proto.survey.Vote} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.survey.Vote)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Vote>|undefined}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServiceClient.prototype.surveyVote =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/survey.SurveyService/SurveyVote',
      request,
      metadata || {},
      methodDescriptor_SurveyService_SurveyVote,
      callback);
};


/**
 * @param {!proto.survey.Vote} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.survey.Vote>}
 *     Promise that resolves to the response
 */
proto.survey.SurveyServicePromiseClient.prototype.surveyVote =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/survey.SurveyService/SurveyVote',
      request,
      metadata || {},
      methodDescriptor_SurveyService_SurveyVote);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.survey.SurveyData,
 *   !proto.survey.Vote>}
 */
const methodDescriptor_SurveyService_GetSurveyVotes = new grpc.web.MethodDescriptor(
  '/survey.SurveyService/GetSurveyVotes',
  grpc.web.MethodType.SERVER_STREAMING,
  proto.survey.SurveyData,
  proto.survey.Vote,
  /**
   * @param {!proto.survey.SurveyData} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.survey.Vote.deserializeBinary
);


/**
 * @param {!proto.survey.SurveyData} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Vote>}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServiceClient.prototype.getSurveyVotes =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/survey.SurveyService/GetSurveyVotes',
      request,
      metadata || {},
      methodDescriptor_SurveyService_GetSurveyVotes);
};


/**
 * @param {!proto.survey.SurveyData} request The request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.survey.Vote>}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServicePromiseClient.prototype.getSurveyVotes =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/survey.SurveyService/GetSurveyVotes',
      request,
      metadata || {},
      methodDescriptor_SurveyService_GetSurveyVotes);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.survey.SurveyData,
 *   !proto.survey.SurveyStats>}
 */
const methodDescriptor_SurveyService_GetSurveyStats = new grpc.web.MethodDescriptor(
  '/survey.SurveyService/GetSurveyStats',
  grpc.web.MethodType.UNARY,
  proto.survey.SurveyData,
  proto.survey.SurveyStats,
  /**
   * @param {!proto.survey.SurveyData} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.survey.SurveyStats.deserializeBinary
);


/**
 * @param {!proto.survey.SurveyData} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.survey.SurveyStats)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.survey.SurveyStats>|undefined}
 *     The XHR Node Readable Stream
 */
proto.survey.SurveyServiceClient.prototype.getSurveyStats =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/survey.SurveyService/GetSurveyStats',
      request,
      metadata || {},
      methodDescriptor_SurveyService_GetSurveyStats,
      callback);
};


/**
 * @param {!proto.survey.SurveyData} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.survey.SurveyStats>}
 *     Promise that resolves to the response
 */
proto.survey.SurveyServicePromiseClient.prototype.getSurveyStats =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/survey.SurveyService/GetSurveyStats',
      request,
      metadata || {},
      methodDescriptor_SurveyService_GetSurveyStats);
};


module.exports = proto.survey;

