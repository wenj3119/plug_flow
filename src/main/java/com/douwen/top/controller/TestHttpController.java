package com.douwen.top.controller;

import com.douwen.top.model.Result;
import com.douwen.top.service.HttpTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test_url")
public class TestHttpController {

    @Autowired
    HttpTestService httpTestService;

    @GetMapping("/get")
    public Result<String> tesGettUrl(
            Integer size,
            String httpUrl
    ) {

        httpTestService.testGetUrl(
                size,
                httpUrl
        );
        return Result.success(null);
    }

    @GetMapping("/post")
    public Result<String> tesPosttUrl(
            Integer size,
            String httpPostUrl
    ) {

        httpTestService.testPostUrl(
                size,
                httpPostUrl
        );
        return Result.success(null);
    }
}
