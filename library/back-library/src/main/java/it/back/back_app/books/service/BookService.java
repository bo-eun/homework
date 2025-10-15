package it.back.back_app.books.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import it.back.back_app.books.dto.AuthorDTO;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.dto.PublishingHouseDTO;
import it.back.back_app.books.entity.AuthorEntity;
import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.entity.BookImageEntity;
import it.back.back_app.books.entity.PublishingHouseEntity;
import it.back.back_app.books.repository.AuthorRepository;
import it.back.back_app.books.repository.BookRepository;
import it.back.back_app.books.repository.PublishingRepository;
import it.back.back_app.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublishingRepository publishingHouseRepository;
    private final FileUtils fileUtils;

    @Value("${server.file.book.path}")
    String filePath;

    @Transactional(readOnly = true)
    public Map<String, Object> getBookList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BookEntity> bookList = bookRepository.findAll(pageable);

        // toList()로 불변객체를 만들어 데이터 변경을 막음
        List<BookDTO> list = bookList.getContent().stream().map(BookDTO::of).toList();

        resultMap.put("total", bookList.getTotalElements());
        resultMap.put("page", bookList.getNumber());
        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getPublishingHouse() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<PublishingHouseDTO> list = publishingHouseRepository.findAll()
                                           .stream()
                                           .map(PublishingHouseDTO::of)
                                           .toList();

        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getAuthor() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<AuthorDTO> list = authorRepository.findAll()
                               .stream()
                               .map(AuthorDTO::of)
                               .toList();

        resultMap.put("content", list);

        return resultMap;
    }    

    @Transactional
    public Map<String, Object> createBook(@ModelAttribute BookDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BookEntity entity = new BookEntity();

        entity = BookDTO.to(request);

        AuthorEntity author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        entity.setAuthor(author);

        PublishingHouseEntity publishingHouseEntity = publishingHouseRepository.findById(request.getPublishingId()).orElseThrow(() -> new RuntimeException("publishingHouse not found"));
        entity.setPublishingHouse(publishingHouseEntity);


        // 기존 파일 삭제
        if(request.getBookImages() != null && !request.getBookImages().isEmpty()) {
            Map<String, Object> fileMap = fileUtils.uploadFiles(request.getBookImages(), filePath);

            if(fileMap != null) {
                BookImageEntity imageEntity = new BookImageEntity();
                imageEntity.setFileName(fileMap.get("fileName").toString());
                imageEntity.setStoredName(fileMap.get("storedFileName").toString());
                imageEntity.setFilePath(fileMap.get("filePath").toString());
                imageEntity.setFileThumbName(filePath);
                imageEntity.setFileSize(request.getBookImages().getSize());

                entity.addFiles(imageEntity);
            } else {
                throw new RuntimeException("파일 업로드 오류");
            }
        }


        bookRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }


    
}
