package org.bookulove.book.api.book.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.db.entity.Book;
import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.bookulove.book.api.book.model.db.entity.ReviewEntity;
import org.bookulove.book.api.book.model.db.repository.BookLibraryRelationRepository;
import org.bookulove.book.api.book.model.feign.UserFeignClient;
import org.bookulove.book.api.book.model.response.BookDetailRes;
import org.bookulove.book.api.book.model.response.RelationDetailRes;
import org.bookulove.book.api.book.model.response.ReviewInfoRes;
import org.bookulove.book.exception.BookServiceException;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.common.util.AuthUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Relation;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookLibraryService {

    private final AuthUtil authUtil;
    private final UserFeignClient userFeignClient;
    private final BookLibraryRelationRepository bookLibraryRelationRepository;

    public RelationDetailRes findBookLibraryInfo(Long buId){
        String token = authUtil.getTokenByHeader();

        BookLibraryRelation bookLibraryRelation = bookLibraryRelationRepository.findById(buId).orElseThrow(
                () -> new BookServiceException(ErrorCode.RELATION_NOT_FOUND)
        );

        List<ReviewInfoRes> reviewInfoResList = new ArrayList<>();
        Book bookEntity = bookLibraryRelation.getBook();
        List<ReviewEntity> reviewEntityList = bookEntity.getReviewEntityList();
        for(ReviewEntity reviewEntity : reviewEntityList){
            ApiData<UserFindInfoRes> userByUserId = userFeignClient.findUserByUserId(token, reviewEntity.getUserId());
            if(userByUserId.status() != 200){
                throw new BookServiceException(ErrorCode.USER_NOT_FOUND);
            }

            ReviewInfoRes reviewInfoRes =
                    ReviewInfoRes.of(reviewEntity.getReviewId(),
                            reviewEntity.getTitle(),
                            reviewEntity.getTitle(),
                            reviewEntity.getUserId(),
                            userByUserId.data().nickname());

            reviewInfoResList.add(reviewInfoRes);
        }

        RelationDetailRes relationDetailRes = RelationDetailRes.of(
                buId,
                bookEntity.getBookId(),
                bookLibraryRelation.getLibrary().getUserId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getPrice(),
                bookEntity.getPubDate(),
                reviewInfoResList
        );

        log.info("책 상세정보 domain: {}", relationDetailRes);

        return relationDetailRes;
    }
}
