package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserFindListPort;
import org.bookulove.user.domain.UserDomain;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserFindListAdapter implements UserFindListPort {

    private final UserRepository userRepository;

    @Override
    public List<UserDomain> findUserList() {

        List<UserDomain> userDomainList = userRepository.findAll().stream()
                .map(UserDomain::of)
                .collect(Collectors.toList());

        log.info("회원 정보 리스트: {}", userDomainList);

        return userDomainList;
    }
}
