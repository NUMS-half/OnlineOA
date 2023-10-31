package cn.edu.neu.onlineoa.mapper;

import cn.edu.neu.onlineoa.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * interface of user data access
 */
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUser();

    @Select("select * from user where identityId = 2")
    List<User> findAllTeacher();

    @Select("select * from user where identityId = 3")
    List<User> findAllHeadTeacher();

    @Select("select * from user where uid = #{uid}")
    User findUserByUid(String uid);

    @Delete("delete from user where uid = #{uid}")
    int deleteUserByUid(String uid) throws Exception;

    @Insert("insert into user (uid, username, password, identityId) values (#{uid}, #{username}, #{password}, #{identityId})")
    int addUser(User user) throws Exception;

    @Update("update user set username=#{username}, password=#{password}, identityId=#{identityId} where uid=#{uid}")
    int updateUser(User user) throws Exception;

    List<User> findUserWithMultiCondition(User user);
}
