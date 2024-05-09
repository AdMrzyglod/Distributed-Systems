package org.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: survey.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SurveyServiceGrpc {

  private SurveyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "survey.SurveyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.grpc.gen.Survey,
      org.grpc.gen.Survey> getCreateSurveyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSurvey",
      requestType = org.grpc.gen.Survey.class,
      responseType = org.grpc.gen.Survey.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.grpc.gen.Survey,
      org.grpc.gen.Survey> getCreateSurveyMethod() {
    io.grpc.MethodDescriptor<org.grpc.gen.Survey, org.grpc.gen.Survey> getCreateSurveyMethod;
    if ((getCreateSurveyMethod = SurveyServiceGrpc.getCreateSurveyMethod) == null) {
      synchronized (SurveyServiceGrpc.class) {
        if ((getCreateSurveyMethod = SurveyServiceGrpc.getCreateSurveyMethod) == null) {
          SurveyServiceGrpc.getCreateSurveyMethod = getCreateSurveyMethod =
              io.grpc.MethodDescriptor.<org.grpc.gen.Survey, org.grpc.gen.Survey>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSurvey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Survey.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Survey.getDefaultInstance()))
              .setSchemaDescriptor(new SurveyServiceMethodDescriptorSupplier("CreateSurvey"))
              .build();
        }
      }
    }
    return getCreateSurveyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.grpc.gen.ListData,
      org.grpc.gen.Survey> getListSurveysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListSurveys",
      requestType = org.grpc.gen.ListData.class,
      responseType = org.grpc.gen.Survey.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.grpc.gen.ListData,
      org.grpc.gen.Survey> getListSurveysMethod() {
    io.grpc.MethodDescriptor<org.grpc.gen.ListData, org.grpc.gen.Survey> getListSurveysMethod;
    if ((getListSurveysMethod = SurveyServiceGrpc.getListSurveysMethod) == null) {
      synchronized (SurveyServiceGrpc.class) {
        if ((getListSurveysMethod = SurveyServiceGrpc.getListSurveysMethod) == null) {
          SurveyServiceGrpc.getListSurveysMethod = getListSurveysMethod =
              io.grpc.MethodDescriptor.<org.grpc.gen.ListData, org.grpc.gen.Survey>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListSurveys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.ListData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Survey.getDefaultInstance()))
              .setSchemaDescriptor(new SurveyServiceMethodDescriptorSupplier("ListSurveys"))
              .build();
        }
      }
    }
    return getListSurveysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.grpc.gen.Vote,
      org.grpc.gen.Vote> getSurveyVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SurveyVote",
      requestType = org.grpc.gen.Vote.class,
      responseType = org.grpc.gen.Vote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.grpc.gen.Vote,
      org.grpc.gen.Vote> getSurveyVoteMethod() {
    io.grpc.MethodDescriptor<org.grpc.gen.Vote, org.grpc.gen.Vote> getSurveyVoteMethod;
    if ((getSurveyVoteMethod = SurveyServiceGrpc.getSurveyVoteMethod) == null) {
      synchronized (SurveyServiceGrpc.class) {
        if ((getSurveyVoteMethod = SurveyServiceGrpc.getSurveyVoteMethod) == null) {
          SurveyServiceGrpc.getSurveyVoteMethod = getSurveyVoteMethod =
              io.grpc.MethodDescriptor.<org.grpc.gen.Vote, org.grpc.gen.Vote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SurveyVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Vote.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Vote.getDefaultInstance()))
              .setSchemaDescriptor(new SurveyServiceMethodDescriptorSupplier("SurveyVote"))
              .build();
        }
      }
    }
    return getSurveyVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.grpc.gen.SurveyData,
      org.grpc.gen.Vote> getGetSurveyVotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSurveyVotes",
      requestType = org.grpc.gen.SurveyData.class,
      responseType = org.grpc.gen.Vote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.grpc.gen.SurveyData,
      org.grpc.gen.Vote> getGetSurveyVotesMethod() {
    io.grpc.MethodDescriptor<org.grpc.gen.SurveyData, org.grpc.gen.Vote> getGetSurveyVotesMethod;
    if ((getGetSurveyVotesMethod = SurveyServiceGrpc.getGetSurveyVotesMethod) == null) {
      synchronized (SurveyServiceGrpc.class) {
        if ((getGetSurveyVotesMethod = SurveyServiceGrpc.getGetSurveyVotesMethod) == null) {
          SurveyServiceGrpc.getGetSurveyVotesMethod = getGetSurveyVotesMethod =
              io.grpc.MethodDescriptor.<org.grpc.gen.SurveyData, org.grpc.gen.Vote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSurveyVotes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.SurveyData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.Vote.getDefaultInstance()))
              .setSchemaDescriptor(new SurveyServiceMethodDescriptorSupplier("GetSurveyVotes"))
              .build();
        }
      }
    }
    return getGetSurveyVotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.grpc.gen.SurveyData,
      org.grpc.gen.SurveyStats> getGetSurveyStatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSurveyStats",
      requestType = org.grpc.gen.SurveyData.class,
      responseType = org.grpc.gen.SurveyStats.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.grpc.gen.SurveyData,
      org.grpc.gen.SurveyStats> getGetSurveyStatsMethod() {
    io.grpc.MethodDescriptor<org.grpc.gen.SurveyData, org.grpc.gen.SurveyStats> getGetSurveyStatsMethod;
    if ((getGetSurveyStatsMethod = SurveyServiceGrpc.getGetSurveyStatsMethod) == null) {
      synchronized (SurveyServiceGrpc.class) {
        if ((getGetSurveyStatsMethod = SurveyServiceGrpc.getGetSurveyStatsMethod) == null) {
          SurveyServiceGrpc.getGetSurveyStatsMethod = getGetSurveyStatsMethod =
              io.grpc.MethodDescriptor.<org.grpc.gen.SurveyData, org.grpc.gen.SurveyStats>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSurveyStats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.SurveyData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.grpc.gen.SurveyStats.getDefaultInstance()))
              .setSchemaDescriptor(new SurveyServiceMethodDescriptorSupplier("GetSurveyStats"))
              .build();
        }
      }
    }
    return getGetSurveyStatsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SurveyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SurveyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SurveyServiceStub>() {
        @java.lang.Override
        public SurveyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SurveyServiceStub(channel, callOptions);
        }
      };
    return SurveyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SurveyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SurveyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SurveyServiceBlockingStub>() {
        @java.lang.Override
        public SurveyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SurveyServiceBlockingStub(channel, callOptions);
        }
      };
    return SurveyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SurveyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SurveyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SurveyServiceFutureStub>() {
        @java.lang.Override
        public SurveyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SurveyServiceFutureStub(channel, callOptions);
        }
      };
    return SurveyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createSurvey(org.grpc.gen.Survey request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Survey> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSurveyMethod(), responseObserver);
    }

    /**
     */
    default void listSurveys(org.grpc.gen.ListData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Survey> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListSurveysMethod(), responseObserver);
    }

    /**
     */
    default void surveyVote(org.grpc.gen.Vote request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Vote> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSurveyVoteMethod(), responseObserver);
    }

    /**
     */
    default void getSurveyVotes(org.grpc.gen.SurveyData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Vote> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSurveyVotesMethod(), responseObserver);
    }

    /**
     */
    default void getSurveyStats(org.grpc.gen.SurveyData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.SurveyStats> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSurveyStatsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SurveyService.
   */
  public static abstract class SurveyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SurveyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SurveyService.
   */
  public static final class SurveyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SurveyServiceStub> {
    private SurveyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SurveyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SurveyServiceStub(channel, callOptions);
    }

    /**
     */
    public void createSurvey(org.grpc.gen.Survey request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Survey> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSurveyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listSurveys(org.grpc.gen.ListData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Survey> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListSurveysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void surveyVote(org.grpc.gen.Vote request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Vote> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSurveyVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSurveyVotes(org.grpc.gen.SurveyData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.Vote> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetSurveyVotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSurveyStats(org.grpc.gen.SurveyData request,
        io.grpc.stub.StreamObserver<org.grpc.gen.SurveyStats> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSurveyStatsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SurveyService.
   */
  public static final class SurveyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SurveyServiceBlockingStub> {
    private SurveyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SurveyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SurveyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.grpc.gen.Survey createSurvey(org.grpc.gen.Survey request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSurveyMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.grpc.gen.Survey> listSurveys(
        org.grpc.gen.ListData request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListSurveysMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.grpc.gen.Vote surveyVote(org.grpc.gen.Vote request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSurveyVoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.grpc.gen.Vote> getSurveyVotes(
        org.grpc.gen.SurveyData request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetSurveyVotesMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.grpc.gen.SurveyStats getSurveyStats(org.grpc.gen.SurveyData request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSurveyStatsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SurveyService.
   */
  public static final class SurveyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SurveyServiceFutureStub> {
    private SurveyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SurveyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SurveyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.grpc.gen.Survey> createSurvey(
        org.grpc.gen.Survey request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSurveyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.grpc.gen.Vote> surveyVote(
        org.grpc.gen.Vote request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSurveyVoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.grpc.gen.SurveyStats> getSurveyStats(
        org.grpc.gen.SurveyData request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSurveyStatsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SURVEY = 0;
  private static final int METHODID_LIST_SURVEYS = 1;
  private static final int METHODID_SURVEY_VOTE = 2;
  private static final int METHODID_GET_SURVEY_VOTES = 3;
  private static final int METHODID_GET_SURVEY_STATS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_SURVEY:
          serviceImpl.createSurvey((org.grpc.gen.Survey) request,
              (io.grpc.stub.StreamObserver<org.grpc.gen.Survey>) responseObserver);
          break;
        case METHODID_LIST_SURVEYS:
          serviceImpl.listSurveys((org.grpc.gen.ListData) request,
              (io.grpc.stub.StreamObserver<org.grpc.gen.Survey>) responseObserver);
          break;
        case METHODID_SURVEY_VOTE:
          serviceImpl.surveyVote((org.grpc.gen.Vote) request,
              (io.grpc.stub.StreamObserver<org.grpc.gen.Vote>) responseObserver);
          break;
        case METHODID_GET_SURVEY_VOTES:
          serviceImpl.getSurveyVotes((org.grpc.gen.SurveyData) request,
              (io.grpc.stub.StreamObserver<org.grpc.gen.Vote>) responseObserver);
          break;
        case METHODID_GET_SURVEY_STATS:
          serviceImpl.getSurveyStats((org.grpc.gen.SurveyData) request,
              (io.grpc.stub.StreamObserver<org.grpc.gen.SurveyStats>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateSurveyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.grpc.gen.Survey,
              org.grpc.gen.Survey>(
                service, METHODID_CREATE_SURVEY)))
        .addMethod(
          getListSurveysMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.grpc.gen.ListData,
              org.grpc.gen.Survey>(
                service, METHODID_LIST_SURVEYS)))
        .addMethod(
          getSurveyVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.grpc.gen.Vote,
              org.grpc.gen.Vote>(
                service, METHODID_SURVEY_VOTE)))
        .addMethod(
          getGetSurveyVotesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.grpc.gen.SurveyData,
              org.grpc.gen.Vote>(
                service, METHODID_GET_SURVEY_VOTES)))
        .addMethod(
          getGetSurveyStatsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.grpc.gen.SurveyData,
              org.grpc.gen.SurveyStats>(
                service, METHODID_GET_SURVEY_STATS)))
        .build();
  }

  private static abstract class SurveyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SurveyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.grpc.gen.SurveyProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SurveyService");
    }
  }

  private static final class SurveyServiceFileDescriptorSupplier
      extends SurveyServiceBaseDescriptorSupplier {
    SurveyServiceFileDescriptorSupplier() {}
  }

  private static final class SurveyServiceMethodDescriptorSupplier
      extends SurveyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SurveyServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SurveyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SurveyServiceFileDescriptorSupplier())
              .addMethod(getCreateSurveyMethod())
              .addMethod(getListSurveysMethod())
              .addMethod(getSurveyVoteMethod())
              .addMethod(getGetSurveyVotesMethod())
              .addMethod(getGetSurveyStatsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
