package org.bookulove.auth.application.port.out;

import org.bookulove.auth.domain.AuthCreateDomain;
import org.bookulove.auth.domain.AuthFindDomain;

public interface AuthFindPort {

    AuthFindDomain findAuth(String id);
}
