package cn.edu.neu.onlineoa.service;

import cn.edu.neu.onlineoa.bean.User;
import cn.edu.neu.onlineoa.mapper.UserDao;
import cn.edu.neu.onlineoa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactoryInstance();

    public List<User> findAllUser() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findAllUser();
        }
    }

    public List<User> findAllTeacher() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findAllTeacher();
        }
    }

    public List<User> findAllHeadTeacher() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findAllHeadTeacher();
        }
    }

    public User findUserByUid(String uid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findUserByUid(uid);
        }
    }

    public int deleteUserByUid(String uid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.deleteUserByUid(uid);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int addUser(User user) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.addUser(user);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int updateUser(User user) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.updateUser(user);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public List<User> findUserWithMultiCondition(User user) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findUserWithMultiCondition(user);
        }
    }

}
