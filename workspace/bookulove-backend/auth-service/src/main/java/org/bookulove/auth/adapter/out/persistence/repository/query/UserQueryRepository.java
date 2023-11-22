package org.bookulove.auth.adapter.out.persistence.repository.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

//    public String userNameTest(){
//        return jpaQueryFactory
//                .selectFrom()
//    }

}
