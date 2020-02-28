package com.iogogogo.ssm.service.impl;

import com.iogogogo.ssm.dao.UserDao;
import com.iogogogo.ssm.entity.UserEntity;
import com.iogogogo.ssm.mapper.UserMapper;
import com.iogogogo.ssm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tao.zeng on 2020/2/27.
 */
@Setter
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserEntity> list() {
        return userMapper.findAll();
    }
}
