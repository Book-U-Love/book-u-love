package org.bookulove.user.adapter.in.web;


import jakarta.validation.Valid;
import org.bookulove.user.adapter.in.web.dto.request.UserCreateReq;
import org.bookulove.user.exception.UserServiceException;
import org.bookyoulove.common.api.response.ApiData;
import org.bookyoulove.common.error.ErrorCode;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ApiData<String> createUser(@RequestBody @Valid UserCreateReq req){

        return ApiData.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping
    public ApiData<String> errorTest(){
        throw new UserServiceException(ErrorCode.BAD_REQUEST);
    }

}