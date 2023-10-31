package cn.edu.neu.onlineoa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sessionFactory = null;
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static SqlSessionFactory getSessionFactoryInstance(){
        return sessionFactory;
    }
}
