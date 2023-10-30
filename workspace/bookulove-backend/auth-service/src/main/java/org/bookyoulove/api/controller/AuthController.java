package org.bookyoulove.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.api.controller.dto.request.AuthCreateReq;
import org.bookyoulove.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Slf4j
@RestController
@RequestMapping("api/auths")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authentication")
    public ResponseEntity<String> createAuth(@RequestBody @Valid AuthCreateReq req){
        log.info("AuthCreateReq: {}", req);
//        authService.createAuth(req);
        return ResponseEntity.ok("로그인에 성공하였습니다.");
    }

    @GetMapping("/authentication")
    public ResponseEntity<String> deleteAuth(){

        return ResponseEntity.ok("로그아웃에 성공하였습니다.");
    }


}
