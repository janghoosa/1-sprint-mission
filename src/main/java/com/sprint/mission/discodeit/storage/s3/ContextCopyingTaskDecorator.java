package com.sprint.mission.discodeit.storage.s3;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextCopyingTaskDecorator implements TaskDecorator {
  @Override
  public Runnable decorate(Runnable runnable) {
    // 현재 쓰레드의 MDC와 SecurityContext를 복사
    Map<String, String> contextMap = MDC.getCopyOfContextMap();
    SecurityContext securityContext = SecurityContextHolder.getContext();

    return () -> {
      try {
        // 새로운 쓰레드에 컨텍스트 설정
        if (contextMap != null) {
          MDC.setContextMap(contextMap);
        }
        SecurityContextHolder.setContext(securityContext);
        runnable.run();
      } finally {
        MDC.clear();
        SecurityContextHolder.clearContext();
      }
    };
  }
}
