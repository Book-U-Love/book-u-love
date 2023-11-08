package org.bookulove.book.api.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.request.BookSearchReq;
import org.bookulove.book.api.book.model.request.BookUpdateReq;
import org.bookulove.book.api.book.model.response.BookSearchRes;
import org.bookulove.book.api.book.model.service.BookService;
import org.bookulove.common.api.response.ApiData;
import org.springframework.web.bind.annotation.*;

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
        log.info(logCurrent(getClassName(), getMethodName(), START));
        BookSearchRes res = bookService.search(isbn);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ApiData.ok(res);
    }

    @PostMapping
    ApiData<String> regist(@RequestBody final BookSearchReq bookSearchReq) {
        bookService.regist(bookSearchReq);
        return ApiData.ok("도서 등록이 완료되었습니다.");
    }

    @PatchMapping
    ApiData<String> update(@RequestBody final BookUpdateReq bookUpdateReq) {
        bookService.update(bookUpdateReq);
        return ApiData.ok("도서 수정이 완료되었습니다.");
    }

}