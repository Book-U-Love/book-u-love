package org.bookulove.book.api.book.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.bookulove.book.api.book.model.db.entity.Book;
import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.bookulove.book.api.book.model.db.repository.BookLibraryRelationRepository;
import org.bookulove.book.api.book.model.db.repository.BookRepository;
import org.bookulove.book.api.book.model.request.BookUpdateReq;
import org.bookulove.book.api.book.model.response.BookInfo;
import org.bookulove.book.api.library.model.db.entity.Library;
import org.bookulove.book.api.library.model.db.repository.LibraryRepository;
import org.bookulove.book.exception.BookServiceException;
import org.bookulove.book.api.book.model.feign.AladinFeignClient;
import org.bookulove.book.api.book.model.feign.AladinSearch;
import org.bookulove.book.api.book.model.request.BookRegistReq;
import org.bookulove.book.api.book.model.response.BookSearchRes;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.util.AuthUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bookulove.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final AuthUtil authUtil;
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final BookLibraryRelationRepository bookLibraryRelationRepository;
    private final AladinFeignClient aladinFeignClient;

    @Value("${custom.aladin.key}")
    private String KEY;
    @Value("${custom.aladin.type}")
    private String TYPE;
    @Value("${custom.aladin.output}")
    private String OUTPUT;
    @Value("${custom.aladin.version}")
    private String VERSION;

    public BookSearchRes search(String isbn) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        log.info("ISBN : {}", isbn);

        Book book = bookRepository.findByIsbn(isbn)
                .orElseGet( () -> searchAladinAndSave(isbn) );

        log.info("book : {}", book.getIsbn());
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return new BookSearchRes(book);
    }

    private Book searchAladinAndSave(String isbn) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Response response = aladinFeignClient.aladinSearch(KEY, TYPE, isbn, OUTPUT, VERSION);
        if (response.status() == HttpStatus.OK.value()) {
            String json = null;
            try {
                json = IOUtils.toString(response.body().asInputStream(), Charsets.UTF_8);
            } catch (IOException e) {
                log.info("IOException : {}", e);
                log.info(logCurrent(getClassName(), getMethodName(), END));
                throw new BookServiceException(ErrorCode.IO_ERROR);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = null;
            try {
                map = objectMapper.readValue(json, Map.class);
            } catch (JsonProcessingException e) {
                log.info("JsonProcessingException : {}", e);
                log.info(logCurrent(getClassName(), getMethodName(), END));
                throw new BookServiceException(ErrorCode.JSON_PARSE_ERROR);
            }
            AladinSearch aladinSearch = new AladinSearch((Map<String, Object>) ((ArrayList) map.get("item")).get(0));
            Book book = aladinSearch.to();
            bookRepository.save(book);

            log.info(logCurrent(getClassName(), getMethodName(), END));
            return book;
        } else {    // 알라딘 API 통신에서 오류 발생 시 null 리턴
            log.info("response.status() : {}", response.status());

            log.info(logCurrent(getClassName(), getMethodName(), END));
            throw new BookServiceException(ErrorCode.EXTERNAL_API_ERROR);
        }

    }

    public void regist(BookRegistReq bookRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = null;
        try {
            userId = authUtil.getUserIdByHeader();
        } catch (Exception e) {
            userId = 1l;
        }

        Library library = libraryRepository.findById(userId)
                .orElseThrow( () -> new BookServiceException(ErrorCode.LIBRARY_NOT_FOUND) );
        Book book = bookRepository.findByIsbn(bookRegistReq.isbn())
                .orElseThrow( () -> new BookServiceException(ErrorCode.BOOK_NOT_FOUND) );

        BookLibraryRelation relation = bookRegistReq.to(book, library);

        bookLibraryRelationRepository.save(relation);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public List<BookInfo> getBookList() {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = null;
        try {
            userId = authUtil.getUserIdByHeader();
        } catch (Exception e) {
            userId = 1l;
        }

        Library library = libraryRepository.findById(userId)
                .orElseThrow( () -> new BookServiceException(ErrorCode.LIBRARY_NOT_FOUND) );

        List<BookInfo> res = new ArrayList<>();
        List<BookLibraryRelation> list = bookLibraryRelationRepository.findAllByLibrary(library);
        for (BookLibraryRelation relation : list) {
            res.add(new BookInfo(relation));
        }

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

    public void update(BookUpdateReq bookUpdateReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = null;
        try {
            userId = authUtil.getUserIdByHeader();
        } catch (Exception e) {
            userId = 1l;
        }

        BookLibraryRelation relation = bookLibraryRelationRepository.findById(bookUpdateReq.buid()) // 존재하지 않는 도서-유저 관계인 경우
                .orElseThrow( () -> new BookServiceException(ErrorCode.RELATION_NOT_FOUND) );
        if (!relation.getLibrary().getUserId().equals(userId)) {    // 사용자가 보유하지 않은 도서에 대한 수정 요청인 경우
            throw new BookServiceException(ErrorCode.USER_NOT_HAVE_BOOK_ERROR);
        }

        bookUpdateReq.condition().ifPresent(relation::updateCondition);
        bookUpdateReq.allowSale().ifPresent(relation::updateAllowSale);
        bookUpdateReq.allowBorrow().ifPresent(relation::updateAllowBorrow);
        bookUpdateReq.isBorrow().ifPresent(relation::updateIsBorrow);
        bookUpdateReq.details().ifPresent(relation::updateDetails);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

}
