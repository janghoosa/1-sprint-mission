package com.sprint.mission.discodeit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ReadStatus extends BaseUpdateEntity {

  private Instant lastReadTime;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @ManyToOne
  @JoinColumn(name = "channel_id", nullable = false)
  private Channel channel;

  @JoinColumn(name = "notification_enabled", nullable = false)
  private Boolean notificationEnabled;

  public ReadStatus(
      User owner, Channel channel, Instant lastReadTime,
      Boolean notificationEnabled
  ) {
    this.owner = owner;
    this.channel = channel;
    this.createdAt = Instant.now();
    this.updatedAt = createdAt;
    this.lastReadTime = lastReadTime;
    this.notificationEnabled = notificationEnabled;
  }

  public void updateLastReadTime(Instant lastReadTime) {
    this.lastReadTime = lastReadTime;
  }
}
