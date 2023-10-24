package org.bookulove.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/example")
class ExampleController {

    record Request(@NotBlank String myName) {}

    record Response(String message) {}

    @PostMapping("/greet")
    ResponseEntity<Response> greet(@Valid @RequestBody final Request payload) {
        return ResponseEntity
                .ok(new Response("hello, " + payload.myName() + "!"));
    }
}
