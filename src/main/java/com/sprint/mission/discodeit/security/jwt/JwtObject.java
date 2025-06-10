package com.sprint.mission.discodeit.security.jwt;

import com.sprint.mission.discodeit.dto.user.UserResponse;
import java.time.Instant;

public record JwtObject(
    Instant issueTime,
    Instant expirationTime,
    UserResponse userDto,
    String token
) {

  public boolean isExpired() {
    return expirationTime.isBefore(Instant.now());
  }
}
