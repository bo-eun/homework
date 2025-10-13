package it.back.back_app.books.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.back.back_app.board.dto.BoardDTO;
import it.back.back_app.board.entity.BoardEntity;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Value("${server.file.book.path}")
    String imagePath;

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
}
