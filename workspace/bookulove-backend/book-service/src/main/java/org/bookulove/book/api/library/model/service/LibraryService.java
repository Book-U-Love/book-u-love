package org.bookulove.book.api.library.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Library;
import org.bookulove.book.api.library.model.db.entity.LibraryEntity;
import org.bookulove.book.api.library.model.db.repository.LibraryRepository;
import org.bookulove.book.api.library.model.request.LibraryCreateCmd;
import org.bookulove.book.exception.BookServiceException;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.bookulove.common.feignclient.book.LibraryUpdateReq;
import org.bookulove.common.util.AuthUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LibraryService {

    private final AuthUtil authUtil;
    private final LibraryRepository libraryRepository;

    public void createLibrary(LibraryCreateCmd cmd) {
        log.info("도서관 생성 cmd: {}", cmd);

        LibraryEntity libraryEntity =
                LibraryEntity.of(cmd.id(), cmd.name(), cmd.lat(), cmd.lng());

        libraryRepository.save(libraryEntity);
    }

    public LibraryFindRes findLibrary() {
        Long userId = authUtil.getUserIdByHeader();

        LibraryEntity libraryEntity = libraryRepository.findById(userId).orElseThrow(
                () -> new BookServiceException(ErrorCode.LIBRARY_NOT_FOUND)
        );

        LibraryFindRes libraryFindRes =
                LibraryFindRes.of(libraryEntity.getUserId(), libraryEntity.getName(), libraryEntity.getLat(), libraryEntity.getLng());

        log.info("도서관 정보: {}", libraryFindRes);
        return libraryFindRes;
    }

    public void updateLibrary(LibraryUpdateReq req) {
        Long userId = authUtil.getUserIdByHeader();
        LibraryEntity libraryEntity = libraryRepository.findById(userId).orElseThrow(
                () -> new BookServiceException(ErrorCode.LIBRARY_NOT_FOUND)
        );

        libraryEntity.update(req.name(), req.lat(), req.lng());
    }

    public LibraryFindListRes findLibraryList() {
        LibraryFindListRes libraryFindListRes = LibraryFindListRes.of(libraryRepository.findAll().stream()
                .map(
                        p -> {
                            return LibraryFindRes.of(p.getUserId(), p.getName(), p.getLat(), p.getLng());
                        }
                ).collect(Collectors.toList()));

        log.info("도서관 목록 res: {}", libraryFindListRes);
        return libraryFindListRes;
    }


}
