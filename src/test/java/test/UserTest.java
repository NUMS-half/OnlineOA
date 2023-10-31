package test;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.service.UserService;
import org.junit.Test;

import java.util.List;

public class UserTest {
    @Test
    public void testFindAllUser() {
        UserService userService = new UserService();
        List<User> list = userService.findAllUser();

        for ( User user : list ) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserByUid() {
        UserService userService = new UserService();
        User user = userService.findUserByUid("20210001");
        System.out.println(user);
    }

    @Test
    public void testDeleteUserByUid() {
        UserService userService = new UserService();
        System.out.println(userService.deleteUserByUid("20210005"));
    }

    @Test
    public void testAddUser() {
        UserService userService = new UserService();
        User user = new User("20210005", "凯多", "123456", 1);
        System.out.println(userService.addUser(user));
    }

    @Test
    public void testUpdateUser() {
        UserService userService = new UserService();
        User user = new User("20210003", "Bob3", "123456", 1);
        System.out.println(userService.updateUser(user));
    }

    @Test
    public void testFindMultiCondition() {
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("zhangsan");
        List<User> list = userService.findUserWithMultiCondition(user);
        for ( User u : list ) {
            System.out.println(u);
        }
    }
}
