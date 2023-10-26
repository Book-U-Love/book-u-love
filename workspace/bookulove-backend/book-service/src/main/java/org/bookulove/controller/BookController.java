package org.bookulove.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
class BookController {

    record Request(@NotBlank String isbn) {}

    record Response(String message) {}

    @PostMapping()
    ResponseEntity<Response> regist(@Valid @RequestBody final Request payload) {
        return ResponseEntity
                .ok(new Response("You searched " + payload.isbn() + "!"));
    }
}