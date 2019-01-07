package com.scientist.lib;

import java.util.HashMap;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/3
 * Time: 下午3:30
 * Desc: RecyclerView Item ViewModel
 */
public interface ItemViewModel<T> {

    T getData();

    int getLayoutId();

    int getVariableId();

    void onPayloadChange(HashMap<String, Object> payloads);
}
