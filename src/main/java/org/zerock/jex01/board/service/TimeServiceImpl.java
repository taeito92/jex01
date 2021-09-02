package org.zerock.jex01.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.jex01.board.mapper.TimeMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService{

    private final TimeMapper timeMapper;

    @Override
    public String getNow() {

        log.info("service...........getNow()");

        return timeMapper.getTime2(); //xml로 작성한 코드
    }
}
