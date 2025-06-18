package com.sprint.mission.discodeit.event;

import java.time.Instant;

public record AsyncTaskFailedEvent(
    Instant createdAt,
    AsyncTaskFailure asyncTaskFailure
) {

  public AsyncTaskFailedEvent(AsyncTaskFailure asyncTaskFailure) {
    this(Instant.now(), asyncTaskFailure);
  }
}