package org.bookulove.api.book.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.bookulove.api.book.model.db.entity.Book;
import org.bookulove.api.book.model.db.repository.BookRepository;
import org.bookulove.api.book.model.feign.AladinFeignClient;
import org.bookulove.api.book.model.feign.AladinSearch;
import org.bookulove.api.book.model.response.BookSearchRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.bookulove.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AladinFeignClient aladinFeignClient;

    @Value("${custom.aladin.key}")
    private String KEY;
    @Value("${custom.aladin.type}")
    private String TYPE;
    @Value("${custom.aladin.output}")
    private String OUTPUT;
    @Value("${custom.aladin.version}")
    private String VERSION;

    public BookSearchRes search(String payload) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Book book = bookRepository.findByIsbn(payload)
                .orElse(searchAladinAndSave(payload));

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return new BookSearchRes(book);
    }

    public Book searchAladinAndSave(String isbn) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Response response = aladinFeignClient.aladinSearch(KEY, TYPE, isbn, OUTPUT, VERSION);
        if (response.status() == HttpStatus.OK.value()) {
            String json = null;
            try {
                json = IOUtils.toString(response.body().asInputStream(), Charsets.UTF_8);
            } catch (IOException e) {
                log.info("IOException : {}", e);
                log.info(logCurrent(getClassName(), getMethodName(), END));
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = null;
            try {
                map = objectMapper.readValue(json, Map.class);
            } catch (JsonProcessingException e) {
                log.info("JsonProcessingException : {}", e);
                log.info(logCurrent(getClassName(), getMethodName(), END));
                return null;
            }
            AladinSearch aladinSearch = new AladinSearch((Map<String, Object>) ((ArrayList) map.get("item")).get(0));
            Book book = aladinSearch.to();
            bookRepository.save(book);
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return book;
        } else {    // 알라딘 API 통신에서 오류 발생 시 null 리턴
            log.info("response.status() : {}", response.status());
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return null;
        }

    }

}
