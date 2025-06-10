package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.controller.api.AuthApi;
import com.sprint.mission.discodeit.dto.auth.RoleUpdateRequest;
import com.sprint.mission.discodeit.dto.user.UserResponse;
import com.sprint.mission.discodeit.security.jwt.JwtService;
import com.sprint.mission.discodeit.security.jwt.JwtSession;
import com.sprint.mission.discodeit.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

  private final AuthService authService;
  private final JwtService jwtService;

  @Override
  @GetMapping("/csrf-token")
  public CsrfToken csrf(CsrfToken csrfToken) {
    return csrfToken;
  }

  @GetMapping("me")
  public ResponseEntity<String> me(
      @CookieValue(value = JwtService.REFRESH_TOKEN_COOKIE_NAME) String refreshToken
  ) {
    log.info("내 정보 조회 요청");
    JwtSession jwtSession = jwtService.getJwtSession(refreshToken);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(jwtSession.getAccessToken());
  }

  @PutMapping("role")
  public ResponseEntity<UserResponse> role(@RequestBody RoleUpdateRequest request) {
    UserResponse userDto = authService.updateRole(request);

    return ResponseEntity.status(HttpStatus.OK).body(userDto);
  }
}
