package com.iogogogo.ssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tao.zeng on 2020/2/28.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    private long id;

    private String name;

    private float money;
}
