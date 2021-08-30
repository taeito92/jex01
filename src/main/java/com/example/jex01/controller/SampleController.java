package com.example.jex01.controller;

import com.example.jex01.service.Hello;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {

   @Autowired
   private Hello hello;

   @GetMapping("/hello")
    public void hello() {

       log.info("--------hello----------");
       log.info("--------hello----------");
       log.info(hello);
       log.info("--------hello----------");
       log.info("--------hello----------");

   }


}
