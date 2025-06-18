package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.event.AsyncTaskFailure;
import com.sprint.mission.discodeit.repository.AsyncTaskFailureRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicAsyncTaskFailureService implements
                                          com.sprint.mission.discodeit.service.AsyncTaskFailureService {

  private final AsyncTaskFailureRepository repository;

  @Override
  public void saveFailure(String taskName, String reason) {
    String requestId = MDC.get("requestId");

    AsyncTaskFailure failure = new AsyncTaskFailure(
        taskName,
        requestId,
        reason
    );
    repository.save(failure);
  }
}
