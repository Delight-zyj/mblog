package com.mblog.controller;

import com.mblog.entry.Digital;
import com.mblog.entry.Result;
import com.mblog.service.DigitalService;
import list.DigitalQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/digital")
@Slf4j
public class DigitalController {

    @Autowired
    private DigitalService digitalService;

    @GetMapping()
    public Result all( DigitalQueryParam digitalQueryParam){
        log.info("查询参数:{}",digitalQueryParam);
        List<Digital> digital = digitalService.selectAll(digitalQueryParam);
        return Result.success(digital);

    }

    

}
