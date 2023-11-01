package org.bookulove.api.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.api.book.model.request.BookSearchReq;
import org.bookulove.api.book.model.response.BookSearchRes;
import org.bookulove.api.book.model.service.BookService;
import org.bookyoulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

import static org.bookyoulove.common.util.LogCurrent.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
class BookController {

    private final BookService bookService;

//    record Request(@NotBlank String isbn) {}
//
//    record Response(String message) {}

    @GetMapping("/search")
    ApiData<BookSearchRes> search(@Valid @RequestParam final String isbn) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        BookSearchRes res = bookService.search(isbn);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ApiData.ok(res);
    }

    @PostMapping
    ApiData<String> regist(@RequestBody final BookSearchReq bookSearchReq) {
        bookService.regist(bookSearchReq);
        return ApiData.ok("성공~~");
    }

    @PostMapping("/test")
    ApiData<String> testLibrary() {
        bookService.testLibrary();

        return ApiData.ok("성공~!~!");
    }

}