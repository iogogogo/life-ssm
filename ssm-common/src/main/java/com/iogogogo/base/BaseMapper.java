package com.iogogogo.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tao.zeng on 2020/2/27.
 */
public interface BaseMapper<T, ID extends Serializable> {

    T save(T entity);

    boolean removeById(ID id);

    boolean remove(T entity);

    boolean updateById(ID id);

    boolean update(T entity);

    List<T> findAll();

    T findById(ID id);
}
