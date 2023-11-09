package org.bookulove.user.adapter.in.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.feignclient.user.UserFindRes;
import org.bookulove.user.adapter.in.web.dto.request.UserCreateReq;
import org.bookulove.user.adapter.in.web.dto.request.UserUpdatePasswordReq;
import org.bookulove.user.adapter.in.web.dto.request.UserUpdateReq;
import org.bookulove.user.application.port.in.UserCreateUseCase;
import org.bookulove.user.application.port.in.UserFindUseCase;
import org.bookulove.user.application.port.in.UserUpdatePasswordUseCase;
import org.bookulove.user.application.port.in.UserUpdateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;
import org.bookulove.user.application.port.in.dto.command.UserUpdatePasswordCmd;
import org.bookulove.user.exception.UserServiceException;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@WebAdapter
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final BCryptPasswordEncoder encoder;
    private final UserFindUseCase userFindUseCase;
    private final UserCreateUseCase userCreateUseCase;
    private final UserUpdateUseCase userUpdateUseCase;
    private final UserUpdatePasswordUseCase userUpdatePasswordUseCase;

    @PostMapping
    public ApiData<String> createUser(@RequestBody @Valid UserCreateReq req){
        log.info("회원가입 req: {}", req.toString());

        userCreateUseCase.createUser(UserCreateCmd.of(req, encoder.encode(req.password())));
        return ApiData.ok("회원가입에 성공하였습니다.");
    }

    @GetMapping
    public ApiData<UserFindRes> findUser(){
        log.info("회원조회");
        return ApiData.ok(userFindUseCase.findUser());
    }

    @PatchMapping
    public ApiData<String> updateUser(@RequestBody UserUpdateReq req){
        log.info("회원정보수정 req: {}", req.toString());

        String encodedPwd = (req.password() != null) ? encoder.encode(req.password()) : null;
        userUpdateUseCase.updateUser(UserUpdateCmd.of(req, encodedPwd));

        return ApiData.ok("회원정보수정에 성공하였습니다.");
    }

    @PatchMapping("/password")
    public ApiData<String> updatePassword(@RequestBody @Valid UserUpdatePasswordReq req){
        log.info("비밀변호 변경 req: {}", req.toString());

        userUpdatePasswordUseCase
                .updatePassword(UserUpdatePasswordCmd.of(req.oldPassword(), encoder.encode(req.newPassword())));

        return ApiData.ok("비밀번호 변경에 성공하였습니다.");
    }

}