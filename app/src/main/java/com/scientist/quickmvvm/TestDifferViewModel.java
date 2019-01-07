package com.scientist.quickmvvm;

import com.scientist.lib.ItemViewModel;
import com.scientist.quickmvvm.differ.Person;

import java.util.ArrayList;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/4
 * Time: 11:38 AM
 * Desc:
 */
public class TestDifferViewModel {


    public LiveDataList<ItemViewModel> mData = new LiveDataList<>(new ArrayList<>());


    public TestDifferViewModel() {
        mData.add(new PersonItemViewModel(new Person("Johnson", false, 30, "001")));
        mData.add(new PersonItemViewModel(new Person("Kobe", true, 40, "002")));
        mData.add(new PersonItemViewModel(new Person("James", true, 34, "003")));
        mData.add(new PersonItemViewModel(new Person("Wade", true, 35, "004")));
        mData.add(new PersonItemViewModel(new Person("Iverson", true, 42, "005")));
    }
}
