package com.scientist.lib;

import android.util.SparseArray;

import java.util.HashMap;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/3
 * Time: 下午4:27
 * Desc: 描述数据类型是否改变
 */
public interface Differ<T> {

    boolean areItemsTheSame(T t1, T t2);

    boolean areContentsTheSame(T t1, T t2);

    HashMap<String/* propertyName */, Object> getChangePayload(T t1, T t2);
}
