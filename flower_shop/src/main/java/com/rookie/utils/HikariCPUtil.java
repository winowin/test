package com.rookie.utils;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPUtil {
    private static HikariDataSource dataSource = new HikariDataSource();
    static {

        try {
            ClassLoader classLoader = HikariCPUtil.class.getClassLoader();
            InputStream stream = classLoader.getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(stream);
            dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName") );
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUsername(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
        } catch (IOException e) {
            throw new RuntimeException("初始化数据源失败",e);
        }


    }
    public static DataSource getDataSource(){
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取链接失败",e);
        }
    }
}
