package com.iogogogo.ssm.service.impl;

import com.iogogogo.ssm.entity.UserEntity;
import com.iogogogo.ssm.mapper.UserMapper;
import com.iogogogo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by tao.zeng on 2020/2/27.
 */
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserEntity> list() {
        return userMapper.findAll();
    }
}
