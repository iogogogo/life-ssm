package com.iogogogo.ssm.dao.impl;

import com.iogogogo.ssm.dao.UserDao;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tao.zeng on 2020/2/27.
 */
@Setter
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    void list() {
        jdbcTemplate.query("select * from users", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

            }
        });
    }

}
