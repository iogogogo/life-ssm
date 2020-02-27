package com.iogogogo.base;

import com.iogogogo.domain.ResponseWrapper;

/**
 * Created by tao.zeng on 2020/2/27.
 */
public interface BaseResult {

    default <T> ResponseWrapper<?> successful(T data) {
        return result(200, "ok", data);
    }

    default <T> ResponseWrapper<?> result(int code, String message, T data) {
        return ResponseWrapper.builder().code(code).message(message).data(data).build();
    }
}
