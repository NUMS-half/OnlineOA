package cn.edu.neu.onlineoa.mapper;

import cn.edu.neu.onlineoa.bean.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * interface of course data access
 */
public interface CourseDao {
    @Select("select * from course")
    List<Course> findAllCourse();

    @Select("select * from course where cid = #{cid}")
    Course findCourseByCid(String cid);

    @Select("select teacherId from course where cid = #{cid}")
    String findTeacherIdByCid(String cid);

    @Delete("delete from course where cid = #{cid}")
    int deleteCourseByCid(String cid) throws Exception;

    @Insert("insert into course (cid, cname, credit, teacherId, teacherName, takeTime, note) " +
            "values (#{cid}, #{cname}, #{credit}, #{teacherId}, #{teacherName}, #{takeTime}, #{note})")
    int addCourse(Course course) throws Exception;

    @Update("update course set cname=#{cname}, credit=#{credit}, teacherId=#{teacherId}, " +
            "teacherName=#{teacherName}, takeTime=#{takeTime}, note=#{note} where cid=#{cid}")
    int updateCourse(Course course) throws Exception;

    List<Course> findCourseWithMultiCondition(Course course);
}
