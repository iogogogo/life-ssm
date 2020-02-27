package com.iogogogo.ssm.controller;

import com.iogogogo.base.BaseController;
import com.iogogogo.domain.ResponseWrapper;
import com.iogogogo.ssm.entity.UserEntity;
import com.iogogogo.ssm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tao.zeng on 2020/2/27.
 */
@Slf4j
@RestController
@RequestMapping("/api/index")
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseWrapper<?> index() {
        List<UserEntity> list = userService.list();
        log.info("list size:{}", list.size());
        return successful(list);
    }
}
