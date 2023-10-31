package cn.edu.neu.onlineoa.mapper;

import cn.edu.neu.onlineoa.bean.Apply;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * interface of course data access
 */
public interface ApplyDao {

    /**
     * 用于管理员查看所有的申请结果与记录
     * @return apply list
     */
    @Select("select a.aid, status, studentId, studentName, courseId, courseName, applyReason, firstApproveTeaId, secondApproveTeaId, rejectReason, confirm " +
            "from apply a join apply_log al on a.aid = al.aid")
    List<Apply> findAllApply();

    /**
     * 用于查找某学生所有已经确认的申请
     * @param studentId 学生ID
     * @return apply list
     */
    @Select("select a.aid, status, studentId, studentName, courseId, courseName, applyReason, firstApproveTeaId, secondApproveTeaId, rejectReason, confirm " +
            "from apply a join apply_log al on a.aid = al.aid " +
            "where studentId = #{studentId} and confirm = 1")
    List<Apply> findAllConfirmedApply(@Param("studentId") String studentId);

    /**
     * 用于查找所有的申请结果与记录，进行记录导出
     * @return apply list
     */
    @Select("select a.aid, status, studentId, studentName, courseId, courseName, applyReason, applyTime, firstApproveTeaId, firstApproveTime, secondApproveTeaId, secondApproveTime, rejectReason, confirm " +
            "from apply a join apply_log al on a.aid = al.aid " +
            "where status = 3")
    List<Apply> findAllPassedApply();

    /**
     * 按照申请编号在apply表中查找申请
     * @param aid 申请编号
     * @return apply
     */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, rejectReason, confirm " +
            "from apply where aid = #{aid}")
    Apply findApplyByAid(int aid);

    /**
     * 按照学生ID在apply表中查找申请
     * @param studentId 学生ID
     * @return apply list
     */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, rejectReason, confirm " +
            "from apply where studentId = #{studentId}")
    List<Apply> findApplyByStuId(String studentId);

    /* no use now */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, firstTeacherId, secondTeacherId, rejectReason, confirm " +
            "from apply join approval_flow on (courseId = cid) " +
            "where firstTeacherId = #{teacherId}")
    List<Apply> findApplyByTea1Id(String teacherId);

    /* no use now */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, firstTeacherId, secondTeacherId, rejectReason, confirm " +
            "from apply join approval_flow on (courseId = cid) " +
            "where secondTeacherId = #{teacherId}")
    List<Apply> findApplyByTea2Id(String teacherId);

    /**
     * 按照审批流查找第一审批人需要进行审批的申请
     * @param teacherId 第一审批人ID
     * @return apply list
     */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, firstTeacherId, secondTeacherId, rejectReason, confirm " +
            "from apply join approval_flow on (courseId = cid) " +
            "where firstTeacherId = #{teacherId} and status = 1")
    List<Apply> findApplyToApproveByTea1Id(String teacherId);

    /**
     * 按照审批流查找第二审批人需要进行审批的申请
     * @param teacherId 第二审批人ID
     * @return apply list
     */
    @Select("select aid, status, studentId, studentName, courseId, courseName, applyReason, firstTeacherId, secondTeacherId, rejectReason, confirm " +
            "from apply join approval_flow on (courseId = cid) " +
            "where secondTeacherId = #{teacherId} and status = 2")
    List<Apply> findApplyToApproveByTea2Id(String teacherId);

    /**
     * 删除指定申请编号的申请
     * @param aid 指定的申请编号
     * @return 是否删除成功
     * @throws Exception 违反数据库约束等异常
     */
    @Delete("delete from apply where aid = #{aid}")
    int deleteApplyByAid(int aid) throws Exception;

    /**
     * 新建申请插入apply表
     * @param apply 新建的申请信息
     * @return 是否插入成功
     * @throws Exception 违反数据库约束等异常
     */
    @Insert("insert into apply (aid, status, studentId, studentName, courseId, courseName, applyReason, rejectReason, confirm) " +
            "values (#{aid}, #{status}, #{studentId}, #{studentName}, #{courseId}, #{courseName}, #{applyReason}, #{rejectReason}, #{confirm})")
    @Options(useGeneratedKeys = true, keyProperty = "aid")
    int addApply(Apply apply) throws Exception;

    /**
     * 更新apply表中的申请信息
     * @param apply 更新后的申请信息
     * @return 是否更新成功
     * @throws Exception 违反数据库约束等异常
     */
    @Update("update apply set status=#{status}, studentId=#{studentId}, studentName=#{studentName}, " +
            "courseId=#{courseId}, courseName=#{courseName}, applyReason=#{applyReason}, " +
            "rejectReason=#{rejectReason}, confirm=#{confirm} " +
            "where aid=#{aid}")
    int updateApply(Apply apply) throws Exception;

    /**
     * 根据多条件查询指定学生的已经确认的申请
     * @param apply 多条件信息
     * @param studentId 指定的学生ID
     * @return apply list
     */
    List<Apply> findConfirmedApplyWithMultiCondition(@Param("apply") Apply apply, @Param("studentId") String studentId);

    /**
     * 根据多条件查询符合条件的申请，可设置第一或第二审批人ID
     * @param apply 多条件信息
     * @param teacherId1 可指定的第一审批人ID
     * @param teacherId2 可指定的第二审批人ID
     * @return apply list
     */
    List<Apply> findApplyWithMultiCondition(@Param("apply") Apply apply, @Param("teacherId1") String teacherId1, @Param("teacherId2") String teacherId2);

    /**
     * 按照身份不同多条件查找审批老师的审批记录
     * @param uid
     * @param identity
     * @param apply
     * @return
     */
    List<Apply> findApplyHistory(@Param("uid") String uid, @Param("identity") int identity , @Param("apply") Apply apply);
}
