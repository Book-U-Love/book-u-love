package org.bookulove.book.api.library.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookyoulove.common.feignclient.book.LibraryCreateReq;
import org.bookulove.book.api.library.model.service.LibraryService;
import org.bookyoulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping()
    ApiData<String> regist(@RequestBody @Valid LibraryCreateReq libraryRegistReq) {
//        libraryService.regist(libraryRegistReq);
        libraryService.testRegist();

        return ApiData.ok(null);
    }


}
