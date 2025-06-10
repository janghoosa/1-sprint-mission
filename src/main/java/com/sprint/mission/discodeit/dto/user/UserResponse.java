package com.sprint.mission.discodeit.dto.user;

import com.sprint.mission.discodeit.dto.binarycontetnt.BinaryContentResponse;
import com.sprint.mission.discodeit.dto.status.UserStatusResponse;
import com.sprint.mission.discodeit.entity.Role;
import java.util.UUID;

public record UserResponse(UUID id, String username, String email, UserStatusResponse status,
                           BinaryContentResponse profile, Role role) {

}
