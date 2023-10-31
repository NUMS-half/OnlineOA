package cn.edu.neu.onlineoa.service;

import cn.edu.neu.onlineoa.bean.ApprovalFlow;
import cn.edu.neu.onlineoa.mapper.ApplyDao;
import cn.edu.neu.onlineoa.mapper.ApprovalFlowDao;
import cn.edu.neu.onlineoa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ApprovalFlowService {

    SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactoryInstance();

    public List<ApprovalFlow> findAllApprovalFlow() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApprovalFlowDao approvalFlowDao = sqlSession.getMapper(ApprovalFlowDao.class);
            return approvalFlowDao.findAllApprovalFlow();
        }
    }

    public int deleteApprovalFlow(String cid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApprovalFlowDao approvalFlowDao = sqlSession.getMapper(ApprovalFlowDao.class);
            return approvalFlowDao.deleteApprovalFlow(cid);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int addApprovalFlow(ApprovalFlow approvalFlow) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApprovalFlowDao approvalFlowDao = sqlSession.getMapper(ApprovalFlowDao.class);
            return approvalFlowDao.addApprovalFlow(approvalFlow);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int updateApprovalFlow(ApprovalFlow approvalFlow) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApprovalFlowDao approvalFlowDao = sqlSession.getMapper(ApprovalFlowDao.class);
            return approvalFlowDao.updateApprovalFlow(approvalFlow);
        } catch ( Exception e ) {
            return 0;
        }
    }
}
