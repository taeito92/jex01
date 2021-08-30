package com.example.jex01.mapper;

import com.example.jex01.config.RootConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;

@Repository
public interface TimeMapper {

    @Select("select now() from dual")
    String getTime();

}
