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
import org.bookulove.book.api.book.model.db.entity.ReviewEntity;
import org.bookulove.book.api.book.model.db.repository.BookLibraryRelationRepository;
import org.bookulove.book.api.book.model.db.repository.BookRepository;
import org.bookulove.book.api.book.model.db.repository.ReviewRepository;
import org.bookulove.book.api.book.model.db.repository.query.BookLibraryRelationQueryRepository;
import org.bookulove.book.api.book.model.feign.UserFeignClient;
import org.bookulove.book.api.book.model.request.BookUpdateReq;
import org.bookulove.book.api.book.model.response.BookDetailRes;
import org.bookulove.book.api.book.model.response.BookInfo;
import org.bookulove.book.api.book.model.response.ReviewInfoRes;
import org.bookulove.book.api.library.model.db.entity.LibraryEntity;
import org.bookulove.book.api.library.model.db.repository.LibraryRepository;
import org.bookulove.book.api.library.model.db.repository.query.LibraryQueryRepository;
import org.bookulove.book.exception.BookServiceException;
import org.bookulove.book.api.book.model.feign.AladinFeignClient;
import org.bookulove.book.api.book.model.feign.AladinSearch;
import org.bookulove.book.api.book.model.request.BookRegistReq;
import org.bookulove.book.api.book.model.response.BookSearchRes;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
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
    private final UserFeignClient userFeignClient;
    private final ReviewRepository reviewRepository;
    private final LibraryRepository libraryRepository;
    private final AladinFeignClient aladinFeignClient;
    private final LibraryQueryRepository libraryQueryRepository;
    private final BookLibraryRelationRepository bookLibraryRelationRepository;
    private final BookLibraryRelationQueryRepository bookLibraryRelationQueryRepository;

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

        Long userId = authUtil.getUserIdByHeader();

        LibraryEntity library = libraryRepository.findById(userId)
                .orElseThrow( () -> new BookServiceException(ErrorCode.LIBRARY_NOT_FOUND) );
        Book book = bookRepository.findByIsbn(bookRegistReq.isbn())
                .orElseThrow( () -> new BookServiceException(ErrorCode.BOOK_NOT_FOUND) );

        BookLibraryRelation relation = bookRegistReq.to(book, library);

        bookLibraryRelationRepository.save(relation);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public List<BookInfo> getBookList(boolean sale, boolean borrow) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = authUtil.getUserIdByHeader();

        List<BookLibraryRelation> libraryRelationByParam = bookLibraryRelationQueryRepository.findLibraryRelationByParam(userId, sale, borrow);

        List<BookInfo> res = new ArrayList<>();
        for (BookLibraryRelation relation : libraryRelationByParam) {
            res.add(new BookInfo(relation));
        }

        log.info("내 도서관 책 리스트 domain: {}", res);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

    public List<BookInfo> getBookList(Long userId, boolean sale, boolean borrow) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        List<BookLibraryRelation> libraryRelationByParam = bookLibraryRelationQueryRepository.findLibraryRelationByParam(userId, sale, borrow);

        List<BookInfo> res = new ArrayList<>();
        for (BookLibraryRelation relation : libraryRelationByParam) {
            res.add(new BookInfo(relation));
        }

        log.info("내 도서관 책 리스트 domain: {}", res);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

    public void update(BookUpdateReq bookUpdateReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = authUtil.getUserIdByHeader();

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
        bookUpdateReq.isRemoved().ifPresent(relation::deleteRelation);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public BookDetailRes findBookInfo(String isbn, boolean review, boolean sale, boolean borrow){
        String token = authUtil.getTokenByHeader();

        Book bookEntity =  bookRepository.findByIsbn(isbn)
                .orElseGet( () -> searchAladinAndSave(isbn) );

        List<ReviewInfoRes> reviewInfoResList = new ArrayList<>();
        List<ReviewEntity> reviewEntityList = bookEntity.getReviewEntityList();

        if(reviewEntityList != null){
            for(ReviewEntity reviewEntity : reviewEntityList){
                ApiData<UserFindInfoRes> userByUserId = userFeignClient.findUserByUserId(token, reviewEntity.getUserId());
                if(userByUserId.status() != 200){
                    throw new BookServiceException(ErrorCode.USER_NOT_FOUND);
                }

                ReviewInfoRes reviewInfoRes =
                        ReviewInfoRes.of(reviewEntity.getReviewId(),
                                reviewEntity.getTitle(),
                                reviewEntity.getContent(),
                                reviewEntity.getUserId(),
                                userByUserId.data().nickname(),
                                reviewEntity.getCreatedTime());

                reviewInfoResList.add(reviewInfoRes);
            }
        }


        List<BookInfo> saleBookInfoList = getBookListByBookId(bookEntity.getBookId(), true, false);
        List<BookInfo> borrowBookInfoList = getBookListByBookId(bookEntity.getBookId(), false, true);

        BookDetailRes bookDetailRes = BookDetailRes.of(bookEntity.getBookId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getPrice(),
                bookEntity.getPubDate(),
                bookEntity.getCover(),
                reviewInfoResList,
                saleBookInfoList,
                borrowBookInfoList);

        log.info("책 상세정보 domain: {}", bookDetailRes);

        return bookDetailRes;
    }

    public List<BookInfo> getBookListByBookId(Long bookId, boolean sale, boolean borrow) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        List<BookLibraryRelation> libraryRelationByParam = bookLibraryRelationQueryRepository.findLibraryRelationByBookId(bookId, sale, borrow);

        List<BookInfo> res = new ArrayList<>();
        for (BookLibraryRelation relation : libraryRelationByParam) {
            res.add(new BookInfo(relation));
        }

        log.info("내 도서관 책 리스트 domain: {}", res);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return res;
    }

}
