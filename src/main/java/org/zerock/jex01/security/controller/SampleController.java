package org.zerock.jex01.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @GetMapping("/doAll")
    public void doAll() {
        log.warn("doAll.........................activate");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/doMember")
    public void doMember() {
        log.warn("doMember.........................activate");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/doAdmin")
    public void doAdmin() {
        log.warn("doAdmin.........................activate");
    }

}
