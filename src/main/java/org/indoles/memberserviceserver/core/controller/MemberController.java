package org.indoles.memberserviceserver.core.controller;

import lombok.RequiredArgsConstructor;
import org.indoles.memberserviceserver.core.controller.interfaces.Roles;
import org.indoles.memberserviceserver.core.dto.request.MemberChargePointRequest;
import org.indoles.memberserviceserver.core.dto.request.SignInRequestInfo;
import org.indoles.memberserviceserver.core.dto.request.SignUpRequestInfo;
import org.indoles.memberserviceserver.core.domain.enums.Role;
import org.indoles.memberserviceserver.core.dto.response.SignInInfo;
import org.indoles.memberserviceserver.core.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 API
     */

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequestInfo request) {
        memberService.signUp(request);

        return ResponseEntity.ok()
                .build();
    }

    /**
     * 로그인 API
     */

    @PostMapping("/signin")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequestInfo request) {
        memberService.signIn(request);

        return ResponseEntity.ok()
                .build();
    }

    /**
     * 로그아웃 API
     */

    @PostMapping("/signout")
    public ResponseEntity<Void> signOut() {
        return ResponseEntity.ok()
                .build();
    }

    /**
     * 포인트 충전 API
     */

    @Roles({Role.BUYER, Role.SELLER})
    @PostMapping("/points/charge")
    public ResponseEntity<Void> chargePoint(@RequestBody MemberChargePointRequest request) {
        SignInInfo memberInfo = new SignInInfo(request.memberId(), request.role());

        memberService.chargePoint(memberInfo, request.amount());
        return ResponseEntity.ok().build();
    }
}
