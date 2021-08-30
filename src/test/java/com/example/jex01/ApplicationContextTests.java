package com.example.jex01;

import com.example.jex01.config.RootConfig;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ApplicationContextTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ArrayList<String> names; //변수 names와 bean에 준 이름 (names)가 같다면 bean 생성

    @Autowired
    DataSource dataSource;

    @Test
    public void getConnection() throws Exception {

        Connection connection = dataSource.getConnection();
        log.info(connection);
        connection.close();

    }

    @Test
    public void test1() {
        log.info("--------------------------");
        log.info("--------------------------");
        log.info(names);
        log.info("--------------------------");
        log.info("--------------------------");

    }

}
