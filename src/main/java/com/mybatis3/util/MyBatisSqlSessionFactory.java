package com.mybatis3.util;
import java.io.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatisSqlSessionFactory
{
    private static SqlSessionFactory sqlSessionFactory = createSessionFactory();

    private static SqlSessionFactory createSessionFactory() {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession();
    }
}