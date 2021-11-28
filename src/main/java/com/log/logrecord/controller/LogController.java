package com.log.logrecord.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @GetMapping("/test")
    public String showLog(){
        log.warn("日志测试,啦啦啦啦,lalala");

        return "ok";
    }

}
