package com.scientist.lib;

import java.util.HashMap;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/3
 * Time: 下午4:49
 * Desc:
 */
public class DifferManager {

    public static DifferManager getInstance() {
        synchronized (DifferManager.class) {
            if (sInstance == null) {
                synchronized (DifferManager.class) {
                    sInstance = new DifferManager();
                }
            }
        }
        return sInstance;
    }

    private static DifferManager sInstance;

    private HashMap<String/* className */, Differ> mDifferMap = new HashMap<>();

    public void put(String clsName, Differ differ) {
        mDifferMap.put(clsName, differ);
    }

    public Differ get(String clsName) {
        return mDifferMap.get(clsName);
    }
}
