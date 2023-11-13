package org.bookulove.book.api.book.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.response.RelationDetailRes;
import org.bookulove.book.api.book.model.service.BookLibraryService;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/relations")
@RequiredArgsConstructor
public class BookLibraryController {

    private final BookLibraryService bookLibraryService;

    @GetMapping("/{buId}")
    public ApiData<RelationDetailRes> findRelationInfo(@PathVariable Long buId){
        log.info("도서관 등록 책 정보: {}", buId);
        return ApiData.ok(bookLibraryService.findBookLibraryInfo(buId));
    }

    @GetMapping("/count/{userId}")
    public ApiData<Integer> findBookCount(@PathVariable Long userId){
        log.info("도서관 등록 책 개수 req");
        return ApiData.ok(bookLibraryService.findCount(userId));
    }
}
