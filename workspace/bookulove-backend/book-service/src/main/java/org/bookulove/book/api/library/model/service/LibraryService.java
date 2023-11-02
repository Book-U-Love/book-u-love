package org.bookulove.book.api.library.model.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.library.model.db.entity.Library;
import org.bookulove.book.api.library.model.db.repository.LibraryRepository;
import org.bookyoulove.common.feignclient.book.LibraryCreateReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.bookyoulove.common.util.LogCurrent.*;
import static org.bookyoulove.common.util.LogCurrent.END;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public void regist(@Valid LibraryCreateReq libraryRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Library library = Library.builder()
                .userId(1l)
                .name(libraryRegistReq.name())
                .description(libraryRegistReq.description())
                .lat(libraryRegistReq.lat())
                .lng(libraryRegistReq.lng())
                .build();
        libraryRepository.save(library);
        log.info("library : {}", library);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    public void testRegist() {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Library library = Library.builder()
                .userId(1l)
                .name("장덕의 빛")
                .description("장덕 다이소 앞 사거리")
                .lat(35.191034)
                .lng(126.814566)
                .build();
        libraryRepository.save(library);
        log.info("library : {}", library);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }
}
