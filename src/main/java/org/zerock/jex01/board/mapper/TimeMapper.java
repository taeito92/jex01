package org.zerock.jex01.board.mapper;


import org.apache.ibatis.annotations.Select;


public interface TimeMapper {

    @Select("select now()") //어노테이션을 이용하여 sql을 실행하는 방법
    String getTime();

    String getTime2(); //mapper.xml으로 mybatis를 통해 sql을 실행하는 방법
}
