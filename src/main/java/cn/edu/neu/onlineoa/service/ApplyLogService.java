package cn.edu.neu.onlineoa.service;

import cn.edu.neu.onlineoa.bean.Apply;
import cn.edu.neu.onlineoa.mapper.ApplyLogDao;
import cn.edu.neu.onlineoa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ApplyLogService {

    SqlSessionFactory sqlSessionFactory = MybatisUtils.getSessionFactoryInstance();

    public List<Apply> findAllApplyLog() {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyLogDao applyLogDao = sqlSession.getMapper(ApplyLogDao.class);
            return applyLogDao.findAllApplyLog();
        }
    }

    public Apply findApplyLogByAid(int aid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession() ) {
            ApplyLogDao applyLogDao = sqlSession.getMapper(ApplyLogDao.class);
            return applyLogDao.findApplyLogByAid(aid);
        }
    }

    public int deleteApplyLogByAid(int aid) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyLogDao applyLogDao = sqlSession.getMapper(ApplyLogDao.class);
            return applyLogDao.deleteApplyLogByAid(aid);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int insertApplyLog(Apply applyLog) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyLogDao applyLogDao = sqlSession.getMapper(ApplyLogDao.class);
            return applyLogDao.insertApplyLog(applyLog);
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int updateApplyLog(Apply applyLog) {
        try ( SqlSession sqlSession = sqlSessionFactory.openSession(true) ) {
            ApplyLogDao applyLogDao = sqlSession.getMapper(ApplyLogDao.class);
            return applyLogDao.updateApplyLog(applyLog);
        } catch ( Exception e ) {
            return 0;
        }
    }
}
