package org.bookulove.auth.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.in.web.dto.request.SmsCreateReq;
import org.bookulove.auth.adapter.in.web.dto.request.SmsDeleteReq;
import org.bookulove.auth.application.port.in.SmsCreateUseCase;
import org.bookulove.auth.application.port.in.SmsDeleteUseCase;
import org.bookulove.auth.application.port.in.dto.command.SmsCreateCmd;
import org.bookulove.auth.application.port.in.dto.command.SmsDeleteCmd;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

@Slf4j
@WebAdapter
@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsCreateUseCase smsCreateUseCase;
    private final SmsDeleteUseCase smsDeleteUseCase;

    @PostMapping
    public ApiData<String> createSms(@RequestBody @Valid SmsCreateReq req) {
        log.info("핸드폰 번호 req: {}", req.toString());
        smsCreateUseCase.createSms(SmsCreateCmd.of(req));
        return ApiData.ok("인증번호를 확인해주세요.");
    }

    @DeleteMapping
    public ApiData<String> deleteSms(@RequestBody @Valid SmsDeleteReq req){
        log.info("핸드폰 인증 req: {}", req.toString());
        smsDeleteUseCase.deleteSms(SmsDeleteCmd.of(req));
        return ApiData.ok("인증에 성공하였습니다.");
    }
}
