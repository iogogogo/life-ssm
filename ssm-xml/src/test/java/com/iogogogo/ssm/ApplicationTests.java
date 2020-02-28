package com.iogogogo.ssm;

import com.iogogogo.ssm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tao.zeng on 2020/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class ApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void transfer() {
        accountService.transfer("jack.zhang", "kevin.yu", 200);
    }
}
