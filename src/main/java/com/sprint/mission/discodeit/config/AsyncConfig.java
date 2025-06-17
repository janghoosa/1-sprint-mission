package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.storage.s3.ContextCopyingTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

  @Bean(name = "asyncExecutor")
  public Executor asyncExecutor(TaskDecorator contextCopyingTaskDecorator) {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("AsyncExecutor-");
    executor.setTaskDecorator(contextCopyingTaskDecorator);
    executor.initialize();
    return executor;
  }

  @Bean
  public TaskDecorator contextCopyingTaskDecorator() {
    return new ContextCopyingTaskDecorator();
  }
}
