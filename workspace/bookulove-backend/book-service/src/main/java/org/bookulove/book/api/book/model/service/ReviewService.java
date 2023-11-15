package org.bookulove.book.api.book.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.book.model.db.entity.Book;
import org.bookulove.book.api.book.model.db.entity.ReviewEntity;
import org.bookulove.book.api.book.model.db.repository.BookRepository;
import org.bookulove.book.api.book.model.db.repository.ReviewRepository;
import org.bookulove.book.api.book.model.feign.UserFeignClient;
import org.bookulove.book.api.book.model.request.ReviewCreateReq;
import org.bookulove.book.api.book.model.response.ReviewRes;
import org.bookulove.book.exception.BookServiceException;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.common.util.AuthUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final AuthUtil authUtil;
    private final BookRepository bookRepository;
    private final UserFeignClient userFeignClient;
    private final ReviewRepository reviewRepository;

    public void createReview(ReviewCreateReq req) {
        Long userId = authUtil.getUserIdByHeader();

        Book bookEntity = bookRepository.findById(req.bookId()).orElseThrow(
                () -> new BookServiceException(ErrorCode.BOOK_NOT_FOUND)
        );

        ReviewEntity reviewEntity = ReviewEntity.of(req.title(), req.content(), bookEntity, userId);

        reviewRepository.save(reviewEntity);
    }

    public ReviewRes findReview(Long reviewId) {
        Long userId = authUtil.getUserIdByHeader();
        String token = authUtil.getTokenByHeader();

        ApiData<UserFindInfoRes> userInfoByUserId = userFeignClient.findUserByUserId(token, userId);
        if (userInfoByUserId.status() != 200) {
            throw new BookServiceException(ErrorCode.USER_NOT_FOUND);
        }

        ReviewEntity reviewEntity = reviewRepository.findById(reviewId).orElseThrow(
                () -> new BookServiceException(ErrorCode.REVIEW_NOT_FOUND)
        );

        ReviewRes reviewRes = ReviewRes.of(userId,
                userInfoByUserId.data().nickname(),
                reviewEntity.getBook().getBookId(),
                reviewEntity.getBook().getIsbn(),
                reviewEntity.getBook().getTitle(),
                reviewEntity.getBook().getCover(),
                reviewId,
                reviewEntity.getTitle(),
                reviewEntity.getContent(),
                reviewEntity.getCreatedTime());

        log.info("독후감 상세내용 domain: {}", reviewRes);
        return reviewRes;
    }

    public List<ReviewRes> findReviewList(){
        Long userId = authUtil.getUserIdByHeader();
        String token = authUtil.getTokenByHeader();

        ApiData<UserFindInfoRes> userInfoByUserId = userFeignClient.findUserByUserId(token, userId);
        if (userInfoByUserId.status() != 200) {
            throw new BookServiceException(ErrorCode.USER_NOT_FOUND);
        }

        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByUserId(userId);

        List<ReviewRes> reviewResList = null;

        if(reviewEntityList != null){
            reviewResList = reviewEntityList.stream()
                    .map(
                            p -> ReviewRes.of(
                                    userId,
                                    userInfoByUserId.data().nickname(),
                                    p.getBook().getBookId(),
                                    p.getBook().getIsbn(),
                                    p.getBook().getTitle(),
                                    p.getBook().getCover(),
                                    p.getReviewId(),
                                    p.getTitle(),
                                    p.getContent(),
                                    p.getCreatedTime()
                            )
                    )
                    .collect(Collectors.toList());
        }

        return reviewResList;
    }
}
