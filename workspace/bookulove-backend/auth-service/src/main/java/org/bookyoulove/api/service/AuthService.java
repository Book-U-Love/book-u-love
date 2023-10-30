package org.bookyoulove.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.api.controller.dto.request.AuthCreateReq;
import org.bookyoulove.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

//    public String createAuth(AuthCreateReq req){
//        jwtTokenProvider.generate()
//
//    }
}
