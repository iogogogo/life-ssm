package com.iogogogo.ssm.mapper;

import com.iogogogo.base.BaseMapper;
import com.iogogogo.ssm.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by tao.zeng on 2020/2/28.
 */
public interface AccountMapper extends BaseMapper<AccountEntity, Long> {

    /**
     * @param in    转入人
     * @param money 金额
     */
    // @Update("update account set money = money + #{money} where name = #{name}")
    void inMoney(@Param("name") String in, @Param("money") float money);

    /**
     * @param out   转出人
     * @param money 金额
     */
    @Update("update account set money = money - #{money} where name = #{name}")
    void outMoney(@Param("name") String out, @Param("money") float money);
}
