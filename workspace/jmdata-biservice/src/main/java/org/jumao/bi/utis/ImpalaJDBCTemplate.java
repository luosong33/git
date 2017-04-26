package org.jumao.bi.utis;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ImpalaJDBCTemplate {

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        dataSource.setUrl("jdbc:hive2://172.18.203.113:21050/;auth=noSasl");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("select * from hive_student where key = \"1002\";");
        System.out.println();
    }

}