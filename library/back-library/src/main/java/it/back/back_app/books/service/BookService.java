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

import it.back.back_app.books.dto.AuthorDTO;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.dto.BookImageDTO;
import it.back.back_app.books.dto.PublishingHouseDTO;
import it.back.back_app.books.entity.AuthorEntity;
import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.entity.BookImageEntity;
import it.back.back_app.books.entity.BookType;
import it.back.back_app.books.entity.PublishingHouseEntity;
import it.back.back_app.books.repository.AuthorRepository;
import it.back.back_app.books.repository.BookImageRepository;
import it.back.back_app.books.repository.BookRepository;
import it.back.back_app.books.repository.PublishingRepository;
import it.back.back_app.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookImageRepository bookImageRepository;
    private final AuthorRepository authorRepository;
    private final PublishingRepository publishingHouseRepository;
    private final FileUtils fileUtils;

    @Value("${server.file.book.path}")
    String filePath;


    /* 메인 추천 도서 */
    public Map<String,Object> getRecommendMain() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<BookEntity> entity = bookRepository.findTop10ByRecommendationStatusTrueOrderByCreateDateDesc();
        List<BookDTO.RecommendListResponse> list = entity.stream()
                .map(BookDTO.RecommendListResponse::of)
                .toList();
        resultMap.put("content", list);

        return resultMap;
    }

    /* 메인 신상 도서 */
    public Map<String,Object> getNewMain() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<BookEntity> entity = bookRepository.findTop10ByOrderByCreateDateDesc();
        List<BookDTO.ListResponse> list = entity.stream()
                .map(BookDTO.ListResponse::of)
                .toList();
        resultMap.put("content", list);

        return resultMap;
    }

    /* 메인 베스트, 판매량 순 도서 */
    public Map<String,Object> getBestMain() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<BookEntity> books = bookRepository.findTopSellingBooks();
                    
        List<BookDTO.ListResponse> list = books.stream()
                    .map(BookDTO.ListResponse::of)
                    .toList();

        resultMap.put("content", list);
        return resultMap;
    }


    /* 전체 책 리스트 */ 
    @Transactional(readOnly = true)
    public Map<String, Object> getBookList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BookEntity> bookList = bookRepository.findAll(pageable);

        // toList()로 불변객체를 만들어 데이터 변경을 막음
        List<BookDTO.ListResponse> list = bookList.getContent().stream().map(BookDTO.ListResponse::of).toList();

        resultMap.put("total", bookList.getTotalElements());
        resultMap.put("page", bookList.getNumber());
        resultMap.put("content", list);

        return resultMap;
    }


    @Transactional(readOnly = true)
    public Map<String, Object> getRecommendList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BookEntity> bookList = bookRepository.findAll(pageable);

        // toList()로 불변객체를 만들어 데이터 변경을 막음
        List<BookDTO.ListResponse> list = bookList.getContent().stream().map(BookDTO.ListResponse::of).toList();

        resultMap.put("total", bookList.getTotalElements());
        resultMap.put("page", bookList.getNumber());
        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getNewList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BookEntity> bookList = bookRepository.findAll(pageable);

        // toList()로 불변객체를 만들어 데이터 변경을 막음
        List<BookDTO.ListResponse> list = bookList.getContent().stream().map(BookDTO.ListResponse::of).toList();

        resultMap.put("total", bookList.getTotalElements());
        resultMap.put("page", bookList.getNumber());
        resultMap.put("content", list);

        return resultMap;
    }   
 
    /* 메인 베스트, 판매량 순 도서 */
    public Map<String,Object> getBestList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BookEntity> books = bookRepository.findAllOrderBySalesDesc(pageable);

        List<BookDTO.ListResponse> list = books.stream()
                    .map(BookDTO.ListResponse::of)
                    .toList();
                    
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
    public Map<String, Object> getAuthorList() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<AuthorDTO> list = authorRepository.findAll()
                               .stream()
                               .map(AuthorDTO::of)
                               .toList();

        resultMap.put("content", list);

        return resultMap;
    }  

    @Transactional(readOnly = true)
    public Map<String, Object> getBookAndAuthor(int bookId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("책 정보를 찾을 수 없습니다."));

        BookDTO.DetailResponse dto = BookDTO.DetailResponse.of(bookEntity);

        resultMap.put("content", dto);

        return resultMap;
    }   

    @Transactional
    public Map<String, Object> createBook(@ModelAttribute BookDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BookEntity entity = new BookEntity();

        entity = BookDTO.Request.to(request);

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


    @Transactional
    public Map<String, Object> updateBook(@ModelAttribute BookDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BookEntity entity = bookRepository.findByIdWithAuthorAndPublishing(request.getBookId()).orElseThrow(() -> new RuntimeException("찾는 책 정보 없음")); 
        BookDTO.DetailResponse detail = BookDTO.DetailResponse.of(entity);

        entity.setBookName(request.getBookName());
        entity.setBookType(BookType.valueOf(request.getBookType()));
        entity.setPrice(request.getPrice());
        entity.setStock(request.getStock());
        entity.setPageCount(request.getPageCount());
        entity.setShortIntro(request.getShortIntro());
        entity.setIntro(request.getIntro());
        entity.setPublicationDate(request.getPublicationDate());
        entity.setRecommendationStatus(request.getRecommendationStatus());

        AuthorEntity author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        entity.setAuthor(author);

        PublishingHouseEntity publishingHouseEntity = publishingHouseRepository.findById(request.getPublishingId()).orElseThrow(() -> new RuntimeException("publishingHouse not found"));
        entity.setPublishingHouse(publishingHouseEntity);


        log.info("이미지 : " + entity.getBookImages());

        // 첨부 파일이 있다면
        if(request.getBookImages() != null && !request.getBookImages().isEmpty()) {
            Map<String, Object> fileMap = fileUtils.uploadFiles(request.getBookImages(), filePath);
            if(fileMap != null) {
                BookImageEntity fileEntity = new BookImageEntity();
                fileEntity.setFileName(fileMap.get("fileName").toString());
                fileEntity.setStoredName(fileMap.get("storedFileName").toString());
                fileEntity.setFilePath(fileMap.get("filePath").toString());
                fileEntity.setFileSize(request.getBookImages().getSize());

                // 수정한 첨부 파일 추가
                entity.getBookImages().clear();
                entity.addFiles(fileEntity);

                bookRepository.save(entity);

                // 기존 파일 삭제
                for(BookImageDTO file : detail.getBookImages()) {
                    String oldFilePath = filePath + file.getStoredName();
                    fileUtils.deleteFile(oldFilePath);
                }

            } else {
                throw new RuntimeException("파일 업로드 오류");
            }

        } else {
            // 새 이미지가 없고 → 기존 이미지가 없다면?
            if (entity.getBookImages() == null || entity.getBookImages().isEmpty()) {
                throw new RuntimeException("도서 이미지가 필요합니다."); // 필수 검사
            }
            // 기존 이미지 유지
            bookRepository.save(entity);
        }

        // bookRepository.save(entity);
        
        // 기존 파일 삭제
        // if(request.getBookImages() != null && !request.getBookImages().isEmpty()) {
            
        //     for(BookImageDTO file : detail.getBookImages()) {
        //         String oldFilePath = filePath + file.getStoredName();
        //         fileUtils.deleteFile(oldFilePath);
        //     }
            
        // }

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    public Map<String, Object> deleteImage(int imageId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        BookImageEntity imageEntity = bookImageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("파일이 존재하지 않습니다."));

        String oldFilePath = imageEntity.getFilePath() + imageEntity.getStoredName();

        // 실제 저장된 파일 삭제
        fileUtils.deleteFile(oldFilePath);
        // DB에 저장된 파일 정보 삭제
        bookImageRepository.delete(imageEntity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    public Map<String, Object> deleteBook(int bookId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BookEntity entity = bookRepository.findByIdWithAuthorAndPublishing(bookId).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));

        // dto로 변경
        BookDTO.DetailResponse detail = BookDTO.DetailResponse.of(entity);
        // 게시글 삭제 > 고아파일 정책 실행해서 file 디비도 같이 지워짐
        bookRepository.delete(entity);

        // 기존 파일 삭제
        if(detail.getBookImages() != null && !detail.getBookImages().isEmpty()) {
            for(BookImageDTO file : detail.getBookImages()) {
                String oldFilePath = filePath + file.getStoredName();
                fileUtils.deleteFile(oldFilePath);
            }
        }

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }


    /* 책 상세정보 */
    @Transactional(readOnly = true)
    public BookDTO.DetailResponse getBook(int bookId) throws Exception {

        BookEntity entity = bookRepository.findByIdWithAuthorAndPublishing(bookId).orElseThrow(() -> new RuntimeException("책 정보를 불러올 수 없습니다."));

        return BookDTO.DetailResponse.of(entity);
    }
    
}
