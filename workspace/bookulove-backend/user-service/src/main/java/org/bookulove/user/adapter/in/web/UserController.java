package org.bookulove.user.adapter.in.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.adapter.in.web.dto.request.UserCreateReq;
import org.bookulove.user.application.port.in.UserCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookulove.user.domain.UserCreateDomain;
import org.bookulove.user.exception.UserServiceException;
import org.bookyoulove.common.annotation.WebAdapter;
import org.bookyoulove.common.api.response.ApiData;
import org.bookyoulove.common.error.ErrorCode;
import org.springframework.web.bind.annotation.*;


@Slf4j
@WebAdapter
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreateUseCase userCreateUseCase;

    @PostMapping
    public ApiData<String> createUser(@RequestBody @Valid UserCreateReq req){
        log.info("회원가입 req: {}", req.toString());
        userCreateUseCase.createUser(UserCreateCmd.of(req));
        return ApiData.ok("회원가입에 성공하였습니다.");
    }

    @GetMapping
    public ApiData<String> errorTest(){
        throw new UserServiceException(ErrorCode.BAD_REQUEST);
    }

    @PostMapping("/error")
    public ApiData<String> apiTest(@RequestBody @Valid UserCreateReq req){
        return ApiData.ok("테스트 성공!");
    }

}