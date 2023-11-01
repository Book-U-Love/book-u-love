package org.bookulove.book.api.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.library.model.request.LibraryRegistReq;
import org.bookulove.book.api.library.model.service.LibraryService;
import org.bookyoulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping()
//    ApiData<String> regist(@Valid @RequestBody final LibraryRegistReq libraryRegistReq) {
    ApiData<String> regist() {
//        libraryService.regist(libraryRegistReq);
        libraryService.testRegist();

        return ApiData.ok(null);
    }


}
