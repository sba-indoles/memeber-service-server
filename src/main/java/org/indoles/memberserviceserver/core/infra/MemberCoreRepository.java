package org.indoles.memberserviceserver.core.infra;

import lombok.RequiredArgsConstructor;
import org.indoles.memberserviceserver.core.domain.Member;
import org.indoles.memberserviceserver.core.entity.MemberEntity;
import org.indoles.memberserviceserver.core.repository.MemberRepository;
import org.indoles.memberserviceserver.global.util.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberCoreRepository implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public boolean isExist(String signInId) {
        return memberJpaRepository.existsBySignInId(signInId);
    }

    @Override
    public Member save(Member member) {
        MemberEntity entity = Mapper.convertToMemberEntity(member);
        MemberEntity saved = memberJpaRepository.save(entity);
        return Mapper.convertToMember(saved);
    }

    @Override
    public Optional<Member> findById(Long id) {
        Optional<MemberEntity> found = memberJpaRepository.findById(id);
        return found.map(Mapper::convertToMember);
    }

    @Override
    public Optional<Member> findBySignInId(String signInId) {
        Optional<MemberEntity> found = memberJpaRepository.findBySignInId(signInId);
        return found.map(Mapper::convertToMember);
    }
}
