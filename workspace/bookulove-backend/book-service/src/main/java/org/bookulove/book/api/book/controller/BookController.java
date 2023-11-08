package org.bookulove.book.api.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.request.BookRegistReq;
import org.bookulove.book.api.book.model.request.BookUpdateReq;
import org.bookulove.book.api.book.model.response.BookInfo;
import org.bookulove.book.api.book.model.response.BookSearchRes;
import org.bookulove.book.api.book.model.service.BookService;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bookulove.common.util.LogCurrent.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
class BookController {

    private final BookService bookService;

//    record Request(@NotBlank String isbn) {}
//
//    record Response(String message) {}

    @GetMapping("/search")
    ApiData<BookSearchRes> search(@Valid @RequestParam final String isbn) {
        log.info(logCurrent(getClassName(), getMethodName(), VIA));
        return ApiData.ok(bookService.search(isbn));
    }

    @PostMapping
    ApiData<String> regist(@RequestBody final BookRegistReq bookRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        bookService.regist(bookRegistReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ApiData.ok("도서 등록이 완료되었습니다.");
    }

    @GetMapping
    ApiData<List<BookInfo>> getBookList() {
        log.info(logCurrent(getClassName(), getMethodName(), VIA));
        return ApiData.ok(bookService.getBookList());
    }

    @PatchMapping
    ApiData<String> update(@RequestBody final BookUpdateReq bookUpdateReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        bookService.update(bookUpdateReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ApiData.ok("도서 수정이 완료되었습니다.");
    }

}