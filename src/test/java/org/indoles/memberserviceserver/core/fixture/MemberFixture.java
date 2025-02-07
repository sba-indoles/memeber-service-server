package org.indoles.memberserviceserver.core.fixture;

import org.indoles.memberserviceserver.core.domain.Member;
import org.indoles.memberserviceserver.core.domain.Point;
import org.indoles.memberserviceserver.core.domain.enums.Role;

public class MemberFixture {

    private MemberFixture() {
    }

    public static Member createBuyerWithDefaultPoint() {
        return Member.builder()
                .signInId("buyerId")
                .password("password00")
                .role(Role.BUYER)
                .point(new Point(1000L))
                .build();
    }

    public static Member createSellerWithDefaultPoint() {
        return Member.builder()
                .signInId("sellerId")
                .password("password00")
                .role(Role.SELLER)
                .point(new Point(1000L))
                .build();
    }
}
