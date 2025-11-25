package back.smart.code;

import back.smart.code.books.service.BookService;
import back.smart.code.user.dto.UserDTO;
import back.smart.code.user.entity.UserEntity;
import back.smart.code.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.WebContentGenerator;

import java.util.Map;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class SmartBackApplicationTests {

    private final BookService bookService;

    @Autowired
    private WebContentGenerator webContentGenerator;
    @Autowired
    private UserService userService;

    @Autowired
    public SmartBackApplicationTests(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    @Transactional
    void getBooksList() throws  Exception {
        //given
        PageRequest page = PageRequest.of(0, 10);
        //when
        Map<String, Object> resultMap = bookService.getBooksList(page);
        //then
        assertNotNull("결과는 null 이면 안됩니다", resultMap);
        assertNotNull("게시글 리스트는 존재해야합니다", resultMap.get("data"));
    }
        @Test
        @Transactional
        void getBook() throws  Exception {
            //when
            Map<String, Object> resultMap = bookService.getBook("ddff245044");
            //then
            assertNotNull("결과는 null 이면 안됩니다", resultMap);
            assertNotNull("책이 존재해야함", resultMap.get("vo"));
        }

}
