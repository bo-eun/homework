package back.smart.code;

import back.smart.code.user.dto.UserDTO;
import back.smart.code.user.entity.UserRoleEntity;
import back.smart.code.user.repository.UserRoleRepository;
import back.smart.code.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@DisplayName("유저 아이디 중복 확인")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Test
    @Transactional
    void userIdCheck() throws  Exception {
        //given
        UserDTO.Register user = new UserDTO.Register();
        UserRoleEntity role = new UserRoleEntity();
        role.setRoleId("1");
        role.setRoleName("USER");
        role = userRoleRepository.save(role);
        user.setUserId("user01");
        user.setNames("테스트유저");
        user.setPassword("1234");
        user.setEmail("dfd@mfo.com");
        user.setPhone("01011112222");
        user.setBirth("1997-07-26");
        user.setGender("man");
        user.setAddr("...");
        user.setAddrDetail("...");
        user.setRoleId(role.getRoleId()); // 필수!
        userService.registerUser(user);

        // when
        boolean result = userService.checkUserIdExists("user01");
        //then
        assertTrue("아이디 중복!", result);

        boolean result2 = userService.checkUserIdExists("user99");
        assertFalse("아이디 중복되지 않음", result2);
    }
}
