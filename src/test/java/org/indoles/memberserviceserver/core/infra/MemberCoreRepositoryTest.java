package org.indoles.memberserviceserver.core.infra;

import org.indoles.memberserviceserver.core.context.RepositoryTest;
import org.indoles.memberserviceserver.core.domain.Member;
import org.indoles.memberserviceserver.core.domain.Point;
import org.indoles.memberserviceserver.core.domain.enums.Role;
import org.indoles.memberserviceserver.core.fixture.MemberFixture;
import org.indoles.memberserviceserver.core.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberCoreRepositoryTest extends RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    class Member_Save {

        @Test
        @DisplayName("회원 도메인 엔티티를 받으면 정상적으로 저장한다")
        void saveMember_Entity_Then_Success() {
            // given
            Member buyer = Member.builder()
                    .signInId("buyer")
                    .password("password00")
                    .role(Role.BUYER)
                    .point(new Point(1000L))
                    .build();

            // when
            Member saved = memberRepository.save(buyer);

            // then
            assertThat(saved).isNotNull();
            assertThat(saved.getId()).isNotNull();  // ID가 자동 생성되었는지 확인
            assertThat(saved.getSignInId()).isEqualTo(buyer.getSignInId());
            assertThat(saved.getPoint()).isEqualTo(buyer.getPoint());
        }
    }

    @Nested
    class Member_find {

        @Test
        @DisplayName("저장된 회원이면 정상적으로 반환한다")
        void savedMember_Then_Success() {
            // given
            Member buyer = MemberFixture.createBuyerWithDefaultPoint();
            Member saved = memberRepository.save(buyer);

            // when
            Optional<Member> found = memberRepository.findById(saved.getId());

            // then
            assertThat(found).isPresent();
            assertThat(found.get().getId()).isEqualTo(saved.getId());
            assertThat(found.get().getSignInId()).isEqualTo(saved.getSignInId());
        }

        @Test
        @DisplayName("저장되지 않은 회원이면 Optional.empty를 반환한다")
        void unsavedMember_Then_ReturnOptionalEmpty() {
            // when
            Optional<Member> found = memberRepository.findById(1234567890L);

            // then
            assertThat(found).isEmpty();
        }
    }

    @Nested
    class Member_findBySignInId {

        @Test
        @DisplayName("저장된 회원이면 정상적으로 반환한다")
        void savedMember_Then_Success() {
            // given
            String signInId = "findthisid";
            Member buyer = Member.builder()
                    .id(1L)
                    .signInId(signInId)
                    .password("password00")
                    .role(Role.BUYER)
                    .point(new Point(1000L))
                    .build();
            memberRepository.save(buyer);

            // when
            Optional<Member> found = memberRepository.findBySignInId(signInId);

            // then
            assertThat(found).isPresent();
            assertThat(found.get().getSignInId()).isEqualTo("findthisid");
        }

        @Test
        @DisplayName("저장되지 않은 회원이면 Optional.empty를 반환한다")
        void unsavedMember_Then_ReturnOptionalEmpty() {
            // when
            Optional<Member> found = memberRepository.findBySignInId("nonexistent");

            // then
            assertThat(found).isEmpty();
        }
    }
}
