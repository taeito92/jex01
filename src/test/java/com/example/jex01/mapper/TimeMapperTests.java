package com.example.jex01.mapper;

import com.example.jex01.config.RootConfig;
import com.example.jex01.mapper.TimeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TimeMapperTests {


    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void testGetTime1() {
        log.info(timeMapper);
        log.info(timeMapper.getTime());
    }
}
