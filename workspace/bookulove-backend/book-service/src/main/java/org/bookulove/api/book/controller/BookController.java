package org.bookulove.api.book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.api.book.model.request.BookSearchReq;
import org.bookulove.api.book.model.response.BookSearchRes;
import org.bookulove.api.book.model.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.bookulove.common.util.LogCurrent.*;

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
//    ResponseEntity<Response> regist(@Valid @RequestBody final Request payload) {
    ResponseEntity<?> search(@Valid @RequestParam final String payload) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        BookSearchRes res = bookService.search(payload);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity
                .ok(res);
    }

    @PostMapping
    ResponseEntity<?> regist(@RequestBody final BookSearchReq payload) {
        bookService.searchAladinAndSave(payload.isbn());
        return ResponseEntity.ok(null);
    }
}