package com.iogogogo.ssm.dao.impl;

import com.iogogogo.ssm.dao.AccountDao;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by tao.zeng on 2020/2/28.
 */
@Setter
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;
}
