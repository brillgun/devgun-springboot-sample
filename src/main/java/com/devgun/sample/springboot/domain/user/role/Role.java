package com.devgun.sample.springboot.domain.user.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자"),
    STAFF("ROLE_STAFF", "직원"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String title;
}
