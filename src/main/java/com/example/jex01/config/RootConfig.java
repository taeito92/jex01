package com.example.jex01.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;

@Configuration //xml 파일을 어노테이션을 줘서 java 코드로 표현하는 것
@ComponentScan(basePackages = {"com.example.jex01.service"})
@ComponentScan(basePackages = {"com.example.jex01.mapper"})
@MapperScan(basePackages = {"com.example.jex01.mapper"})
public class RootConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/springdb");
        hikariConfig.setUsername("springuser");
        hikariConfig.setPassword("springuser");

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return  hikariDataSource;
    }

    @Bean(name = {"names"})
    public ArrayList<String> names() {
        ArrayList<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        return list;
    }

}
