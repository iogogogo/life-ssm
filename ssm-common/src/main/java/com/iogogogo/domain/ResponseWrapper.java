package com.iogogogo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by tao.zeng on 2020/2/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> implements Serializable {

    private T data;

    private String message;

    private int code;
}
