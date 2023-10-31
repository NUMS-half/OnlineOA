package cn.edu.neu.onlineoa.service;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.mapper.ApplyDao;
import cn.edu.neu.onlineoa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ApplyService {

    SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactoryInstance();

    public List<Apply> findAllApply() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findAllApply();
        }
    }

    public List<Apply> findAllConfirmedApply(String studentId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findAllConfirmedApply(studentId);
        }
    }

    public List<Apply> findAllPassedApply() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findAllPassedApply();
        }
    }

    public Apply findApplyByAid(int aid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyByAid(aid);
        }
    }

    public List<Apply> findApplyByStuId(String studentId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyByStuId(studentId);
        }
    }

    public List<Apply> findApplyByTea1Id(String teacherId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyByTea1Id(teacherId);
        }
    }

    public List<Apply> findApplyByTea2Id(String teacherId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyByTea2Id(teacherId);
        }
    }

    public List<Apply> findApplyToApproveByTea1Id(String teacherId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyToApproveByTea1Id(teacherId);
        }
    }

    public List<Apply> findApplyToApproveByTea2Id(String teacherId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyToApproveByTea2Id(teacherId);
        }
    }

    public int deleteApplyByAid(int aid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.deleteApplyByAid(aid);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int addApply(Apply apply) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.addApply(apply);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int updateApply(Apply apply) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.updateApply(apply);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public List<Apply> findConfirmedApplyWithMultiCondition(Apply apply, String studentId) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findConfirmedApplyWithMultiCondition(apply, studentId);
        }
    }

    public List<Apply> findApplyWithMultiCondition(Apply apply, String teacherId1, String teacherId2) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyWithMultiCondition(apply, teacherId1, teacherId2);
        }
    }

    public List<Apply> findApplyHistory( String uid, int identity, Apply apply) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyDao applyDao = sqlSession.getMapper(ApplyDao.class);
            return applyDao.findApplyHistory(uid, identity, apply);
        }
    }
}
