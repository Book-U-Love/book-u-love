package org.bookulove.book.api.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.library.model.request.LibraryCreateCmd;
import org.bookulove.common.feignclient.book.LibraryCreateReq;
import org.bookulove.book.api.library.model.service.LibraryService;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping
    ApiData<String> createLibrary(@RequestBody @Valid LibraryCreateReq req){
        log.info("도서관생성 req: {}", req);
        libraryService.createLibrary(LibraryCreateCmd.of(req));
        return ApiData.ok("도서관 생성이 완료되었습니다.");
    }

    @GetMapping
    ApiData<LibraryFindRes> findLibrary(){
        log.info("도서관 조회");
        return ApiData.ok(libraryService.findLibrary());
    }


}
