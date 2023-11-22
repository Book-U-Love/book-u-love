package org.bookulove.user.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.user.adapter.in.web.dto.request.EvalCreateReq;
import org.bookulove.user.application.port.in.EvalCreateUseCase;
import org.bookulove.user.application.port.in.EvalFindRevieweeListUseCase;
import org.bookulove.user.application.port.in.EvalFindReviewerListUseCase;
import org.bookulove.user.application.port.in.dto.command.EvalCreateCmd;
import org.bookulove.user.domain.EvalFindRevieweeListDomain;
import org.bookulove.user.domain.EvalFindReviewerListDomain;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/evals")
@RequiredArgsConstructor
public class EvalController {

    private final EvalCreateUseCase evalCreateUseCase;
    private final EvalFindRevieweeListUseCase evalFindRevieweeListUseCase;
    private final EvalFindReviewerListUseCase evalFindReviewerListUseCase;

    @PostMapping
    public ApiData<String> createEval(@RequestBody @Valid EvalCreateReq req) {
        log.info("평가 생성 req: {}", req);
        evalCreateUseCase.createEval(EvalCreateCmd.of(req));
        return ApiData.ok("평가 등록이 완료되었습니다.");
    }

    @GetMapping("/reviewee")
    public ApiData<EvalFindRevieweeListDomain> findReviewee(){
        log.info("나에 대한 평가 req");
        return ApiData.ok(evalFindRevieweeListUseCase.findRevieweeList());
    }

    @GetMapping("/reviewee/{userId}")
    public ApiData<EvalFindRevieweeListDomain> findRevieweeById(@PathVariable Long userId){
        log.info("상대방에 대한 평가 req: {}", userId.toString());
        return ApiData.ok(evalFindRevieweeListUseCase.findRevieweeListById(userId));
    }

    @GetMapping("/reviewer")
    public ApiData<EvalFindReviewerListDomain> findReviewer(){
        log.info("내가 한 평가 req");
        return ApiData.ok(evalFindReviewerListUseCase.findReviewerList());
    }

}
