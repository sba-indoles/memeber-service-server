package org.indoles.memberserviceserver.core.dto.request;

import org.indoles.memberserviceserver.core.domain.enums.Role;

import static org.indoles.memberserviceserver.core.dto.validateDto.ValidateMemberDto.validateNotNull;

public record SignInfoRequest(Long id, Role role) {

    public SignInfoRequest {
        validateNotNull(id, "로그인한 사용자의 식별자");
        validateNotNull(role, "로그인한 사용자의 역할");
    }

    public boolean isType(Role role) {
        return this.role.equals(role);
    }
}


