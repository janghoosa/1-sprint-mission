package com.sprint.mission.discodeit.service;

public interface AsyncTaskFailureService {
  void saveFailure(String taskName, String reason);
}
