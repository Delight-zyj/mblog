package com.mblog.controller;

import com.mblog.entry.Digital;
import com.mblog.entry.DigitalBrand;
import com.mblog.entry.DigitalType;
import com.mblog.entry.Result;
import com.mblog.service.DigitalService;
import list.DigitalList;
import list.DigitalQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<DigitalList> DigitalList = digitalService.selectAll(digitalQueryParam);
        return Result.success(DigitalList);
    }



    @GetMapping("/{digitalId}")
    public Result getById(@PathVariable Integer digitalId){
        log.info("根据id查询：",digitalId);
        DigitalList digitalList = digitalService.getById(digitalId);
        return Result.success(digitalList);
    }

    // 查询数码产品类型
    @GetMapping("/type")
    public Result getType(){
        log.info("查询数码产品类型");
        List<DigitalType> digitalType = digitalService.getType();
        return Result.success(digitalType);
    }

    // 查询数码产品品牌
    @GetMapping("/brand/{typeid}")
    public Result getBrand(@PathVariable Integer typeid){
        log.info("查询数码产品品牌,{}",typeid);
        List<DigitalBrand> digitalBrand = digitalService.getBrand(typeid);
        return Result.success(digitalBrand);
    }

    

}
