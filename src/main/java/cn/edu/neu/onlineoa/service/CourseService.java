package cn.edu.neu.onlineoa.service;

import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.mapper.CourseDao;
import cn.edu.neu.onlineoa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CourseService {
    SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactoryInstance();

    public List<Course> findAllCourse() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findAllCourse();
        }
    }

    public Course findCourseByCid(String cid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findCourseByCid(cid);
        }
    }

    public String findTeacherIdByCid(String cid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findTeacherIdByCid(cid);
        }
    }

    public int deleteCourseByCid(String cid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.deleteCourseByCid(cid);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int addCourse(Course course) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.addCourse(course);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int updateCourse(Course course) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.updateCourse(course);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public List<Course> findCourseWithMultiCondition(Course course) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findCourseWithMultiCondition(course);
        }
    }
}
