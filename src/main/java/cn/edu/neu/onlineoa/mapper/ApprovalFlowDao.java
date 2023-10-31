package cn.edu.neu.onlineoa.mapper;

import cn.edu.neu.onlineoa.bean.ApprovalFlow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * interface of approval flow data access
 */
public interface ApprovalFlowDao {

    @Select("select * from approval_flow")
    List<ApprovalFlow> findAllApprovalFlow();

    @Delete("delete from approval_flow where cid = #{cid}")
    int deleteApprovalFlow(String cid) throws Exception;

    @Insert("insert into approval_flow (cid, firstTeacherId, secondTeacherId) " +
            "values (#{cid}, #{firstTeacherId}, #{secondTeacherId})")
    int addApprovalFlow(ApprovalFlow approvalFlow) throws Exception;

    @Update("update approval_flow set firstTeacherId=#{firstTeacherId}, secondTeacherId=#{secondTeacherId} " +
            "where cid=#{cid}")
    int updateApprovalFlow(ApprovalFlow approvalFlow) throws Exception;
}
