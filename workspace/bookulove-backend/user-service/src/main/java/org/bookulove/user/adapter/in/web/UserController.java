package org.bookulove.user.adapter.in.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.user.adapter.in.web.dto.request.UserCreateReq;
import org.bookulove.user.adapter.in.web.dto.request.UserUpdatePasswordReq;
import org.bookulove.user.adapter.in.web.dto.request.UserUpdateReq;
import org.bookulove.user.application.port.in.*;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;
import org.bookulove.user.application.port.in.dto.command.UserUpdatePasswordCmd;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.user.domain.UserFindDetailDomain;
import org.bookulove.user.domain.UserFindInfoListDomain;
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
    private final UserFindListUseCase userFindListUseCase;
    private final UserFindDetailUseCase userFindDetailUseCase;
    private final UserFindLoginIdUseCase userFindLoginIdUseCase;
    private final UserUpdatePasswordUseCase userUpdatePasswordUseCase;

    @PostMapping
    public ApiData<String> createUser(@RequestBody @Valid UserCreateReq req){
        log.info("회원가입 req: {}", req.toString());

        userCreateUseCase.createUser(UserCreateCmd.of(req, encoder.encode(req.password())));
        return ApiData.ok("회원가입에 성공하였습니다.");
    }

    @GetMapping
    public ApiData<UserFindInfoRes> findUser(){
        log.info("회원조회 req");
        return ApiData.ok(userFindUseCase.findUser());
    }

    @GetMapping("/{userId}")
    public ApiData<UserFindInfoRes> findUserByUserId(@PathVariable Long userId){
        log.info("회원조회 req");
        return ApiData.ok(userFindUseCase.findUser(userId));
    }

    @GetMapping("/info")
    public ApiData<UserFindDetailDomain> findUserInfo(){
        log.info("마이페이지 req");
        return ApiData.ok(userFindDetailUseCase.findDetail());
    }

    @GetMapping("/info/{userId}")
    public ApiData<UserFindDetailDomain> findUserInfoByUserId(@PathVariable Long userId){
        log.info("마이페이지 req: {}", userId);
        return ApiData.ok(userFindDetailUseCase.findDetail(userId));
    }

    @PatchMapping
    public ApiData<String> updateUser(@RequestBody UserUpdateReq req){
        log.info("회원정보수정 req: {}", req.toString());

        userUpdateUseCase.updateUser(UserUpdateCmd.of(req));

        return ApiData.ok("회원정보수정에 성공하였습니다.");
    }

    @PatchMapping("/password")
    public ApiData<String> updatePassword(@RequestBody @Valid UserUpdatePasswordReq req){
        log.info("비밀변호 변경 req: {}", req.toString());

        userUpdatePasswordUseCase
                .updatePassword(UserUpdatePasswordCmd.of(req.oldPassword(), encoder.encode(req.newPassword())));

        return ApiData.ok("비밀번호 변경에 성공하였습니다.");
    }

    @GetMapping("/check/{loginId}")
    public ApiData<String> findLoginId(@PathVariable("loginId") String loginId){
        log.info("아이디 중복검사 req: {}", loginId);
        userFindLoginIdUseCase.findLoginId(loginId);
        return ApiData.ok("사용가능한 아이디입니다.");
    }

    @GetMapping("/list")
    public ApiData<UserFindInfoListDomain> findUserList(){
        log.info("유저들 조회 req");
        return ApiData.ok( userFindListUseCase.findInfoList());
    }

}