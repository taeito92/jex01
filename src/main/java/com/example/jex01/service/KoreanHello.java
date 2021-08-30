package com.example.jex01.service;

import org.springframework.stereotype.Service;

@Service
public class KoreanHello implements Hello {

    @Override
    public String sayHello() {
        return "hahihoheho";
    }
}
