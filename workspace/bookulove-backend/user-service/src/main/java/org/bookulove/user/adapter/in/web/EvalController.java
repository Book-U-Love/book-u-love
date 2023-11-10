package org.bookulove.user.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.user.adapter.in.web.dto.request.EvalCreateReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/evals")
@RequiredArgsConstructor
public class EvalController {

    @PostMapping
    public ApiData<String> createEval(@RequestBody @Valid EvalCreateReq req) {
//        log.info();
        return ApiData.ok(null);
    }

}
