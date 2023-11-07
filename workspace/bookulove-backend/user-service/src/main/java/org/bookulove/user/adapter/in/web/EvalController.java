package org.bookulove.user.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequestMapping("/api/user-evals")
@RequiredArgsConstructor
public class EvalController {

    @PostMapping
    public ApiData<String> createEval(){
        return ApiData.ok(null);
    }

}
