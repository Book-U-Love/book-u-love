package org.bookulove.book.api.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.request.ReviewCreateReq;
import org.bookulove.book.api.book.model.response.ReviewRes;
import org.bookulove.book.api.book.model.service.ReviewService;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiData<String> createReview(@RequestBody @Valid ReviewCreateReq req){
        log.info("독후감 작성 req: {}", req);
        reviewService.createReview(req);
        return ApiData.ok("독후감 작성에 성공하였습니다.");
    }

    @GetMapping("/{reviewId}")
    public ApiData<ReviewRes> findReview(@PathVariable Long reviewId) {
        log.info("독후감 번호 req: {}", reviewId);
        return ApiData.ok(reviewService.findReview(reviewId));
    }

}
