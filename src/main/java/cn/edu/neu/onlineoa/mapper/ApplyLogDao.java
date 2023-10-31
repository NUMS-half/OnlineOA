package cn.edu.neu.onlineoa.mapper;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.bean.ApplyStatus;
import cn.edu.neu.onlineoa.service.ApplyLogService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplyLogDao {

    @Select("select * from apply_log")
    List<Apply> findAllApplyLog();

    @Select("select * from apply_log where aid = #{aid}")
    Apply findApplyLogByAid(int aid);

    @Delete("delete from apply_log where aid = #{aid}")
    int deleteApplyLogByAid(int aid) throws Exception;

    @Insert("insert into apply_log (aid, applyTime, reasonFilePath, firstApproveTeaId, firstApproveTime, secondApproveTeaId, secondApproveTime) " +
            "values (#{aid}, #{applyTime}, #{reasonFilePath}, #{firstApproveTeaId}, #{firstApproveTime}, #{secondApproveTeaId}, #{secondApproveTime})")
    int insertApplyLog(Apply applyLog) throws Exception;

    @Update("update apply_log set applyTime=#{applyTime}, firstApproveTeaId=#{firstApproveTeaId}, " +
            "firstApproveTime=#{firstApproveTime}, secondApproveTeaId=#{secondApproveTeaId}, secondApproveTime=#{secondApproveTime} " +
            "where aid = #{aid}")
    int updateApplyLog(Apply applyLog) throws Exception;
}
