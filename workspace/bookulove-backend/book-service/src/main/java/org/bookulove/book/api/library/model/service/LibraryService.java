package org.bookulove.book.api.library.model.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.book.api.library.model.db.entity.Library;
import org.bookulove.book.api.library.model.db.repository.LibraryRepository;
import org.bookulove.book.api.library.model.request.LibraryCreateCmd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public void createLibrary(LibraryCreateCmd cmd){
        log.info("도서관 생성 cmd: {}", cmd);


    }


}
