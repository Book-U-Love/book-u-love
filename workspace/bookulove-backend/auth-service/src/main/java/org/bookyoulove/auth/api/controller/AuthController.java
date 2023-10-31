package org.bookyoulove.auth.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.auth.api.service.AuthService;
import org.bookyoulove.auth.api.controller.dto.request.AuthCreateReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
