package com.iogogogo.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iogogogo.consts.DateTimePattern;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Users)实体类
 *
 * @author tao.zeng
 * @since 2020-02-27 09:56:27
 */
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -39422085161185357L;

    private Long id;

    private String username;

    private String address;

    @JsonFormat(pattern = DateTimePattern.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime birthday;

    @JsonFormat(pattern = DateTimePattern.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime updateTime;

    private Float weight;
}
