// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: survey.proto

// Protobuf Java Version: 4.26.1
package org.grpc.gen;

public interface AnswerStatsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:survey.AnswerStats)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.survey.Answer answer = 1;</code>
   * @return Whether the answer field is set.
   */
  boolean hasAnswer();
  /**
   * <code>.survey.Answer answer = 1;</code>
   * @return The answer.
   */
  org.grpc.gen.Answer getAnswer();
  /**
   * <code>.survey.Answer answer = 1;</code>
   */
  org.grpc.gen.AnswerOrBuilder getAnswerOrBuilder();

  /**
   * <code>int64 numberOfVotes = 2;</code>
   * @return The numberOfVotes.
   */
  long getNumberOfVotes();

  /**
   * <code>double percentVotes = 3;</code>
   * @return The percentVotes.
   */
  double getPercentVotes();
}
