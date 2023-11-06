package org.bookulove.auth.application.port.out;

import org.bookulove.auth.domain.AuthFindUserDomain;

public interface AuthFindUserPort {

    AuthFindUserDomain findUser(String id);
}
