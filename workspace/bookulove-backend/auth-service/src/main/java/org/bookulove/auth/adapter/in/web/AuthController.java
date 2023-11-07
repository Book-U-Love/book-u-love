package org.bookulove.auth.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.in.web.dto.request.AuthCreateReq;
import org.bookulove.auth.application.port.in.AuthCreateUseCase;
import org.bookulove.auth.application.port.in.AuthDeleteUseCase;
import org.bookulove.auth.application.port.in.AuthRecreateUseCase;
import org.bookulove.auth.application.port.in.dto.command.AuthCreateCmd;
import org.bookulove.auth.domain.AuthCreateDomain;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AuthCreateUseCase authCreateUseCase;
    private final AuthDeleteUseCase authDeleteUseCase;
    private final AuthRecreateUseCase authRecreateUseCase;

    @PostMapping
    public ApiData<AuthCreateDomain> createAuth(@RequestBody @Valid AuthCreateReq req){
        log.info("권한 생성 req: {}", req);
        return ApiData.ok(authCreateUseCase.createAuth(AuthCreateCmd.of(req)));
    }

    @DeleteMapping
    public ApiData<String> deleteAuth(){
        authDeleteUseCase.deleteAuth();
        return ApiData.ok("로그아웃이 완료되었습니다.");
    }

    @PostMapping("/refresh")
    public ApiData<String> createRefreshToken(){
        return ApiData.ok(authRecreateUseCase.recreateAuth());
    }

}
