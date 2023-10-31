package test;

import cn.edu.neu.onlineoa.bean.Course;
import cn.edu.neu.onlineoa.service.CourseService;
import org.junit.Test;

import java.util.List;

public class CourseTest {

    @Test
    public void testFindAll() {
        CourseService courseService = new CourseService();
        List<Course> list = courseService.findAllCourse();
        for ( Course c : list ) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindByCid() {
        CourseService courseService = new CourseService();
        System.out.println(courseService.findCourseByCid("A00001"));
    }

    @Test
    public void findTeacher() {
        CourseService courseService = new CourseService();
        System.out.println(courseService.findTeacherIdByCid("A00001"));
    }

    @Test
    public void testDelete() {
        CourseService courseService = new CourseService();
        System.out.println(courseService.deleteCourseByCid("A00014"));
    }

    @Test
    public void testAdd() {
        CourseService courseService = new CourseService();
        Course course = new Course("A00014", "test5", 1, "00210001", "lisi", "待定", "无");
        System.out.println(courseService.addCourse(course));
    }

    @Test
    public void testUpdate() {
        CourseService courseService = new CourseService();
        Course course = new Course("A00014", "test5", 1, "00210001", "lisi", "待定111", "无");
        System.out.println(courseService.updateCourse(course));
    }

    @Test
    public void testFindCourseWithMultiCondition() {
        CourseService courseService = new CourseService();
        Course course = new Course();
        course.setCredit(2.75f);
        List<Course> list = courseService.findCourseWithMultiCondition(course);
        for ( Course c : list ) {
            System.out.println(c);
        }
    }

}
