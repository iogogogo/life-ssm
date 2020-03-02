package com.iogogogo.ssm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tao.zeng on 2020/2/28.
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
