package com.iogogogo.ssm.service.impl;

import com.iogogogo.ssm.dao.AccountDao;
import com.iogogogo.ssm.mapper.AccountMapper;
import com.iogogogo.ssm.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by tao.zeng on 2020/2/28.
 */
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional
    public void transfer(String in, String out, float money) {
        log.info("in:{} out:{} money:{}", in, out, money);
        accountMapper.inMoney(in, money);
        // int i = 1 / 0;
        accountMapper.outMoney(out, money);
    }
}
