package com.sprint.mission.discodeit.error;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AsyncTaskFailure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String taskName;

  private String requestId;

  private String failureReason;

  public AsyncTaskFailure(String taskName, String requestId, String failureReason) {
    this.taskName = taskName;
    this.requestId = requestId;
    this.failureReason = failureReason;
  }
}
