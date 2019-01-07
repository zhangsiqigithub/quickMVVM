package com.scientist.quickmvvm;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.util.SparseArray;

import com.scientist.lib.ItemViewModel;
import com.scientist.quickmvvm.differ.Person;

import java.util.HashMap;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/4
 * Time: 12:00 PM
 * Desc:
 */
public class PersonItemViewModel implements ItemViewModel<Person> {


    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<Integer> age = new MutableLiveData<>();
    public MutableLiveData<Boolean> isMan = new MutableLiveData<>();

    private Person mPerson;
    public PersonItemViewModel(Person person) {
        mPerson = person;
        name.setValue(person.getName());
        age.setValue(person.getAge());
        isMan.setValue(person.isMan());
    }

    @Override
    public Person getData() {
        return mPerson;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_person;
    }

    @Override
    public int getVariableId() {
        return BR.model;
    }

    @Override
    public void onPayloadChange(HashMap<String, Object> payloads) {
        String n = (String) payloads.get("name");
        Boolean m = (Boolean) payloads.get("isMan");
        Integer a = (Integer) payloads.get("age");

        Log.d("MyTag", "onPayloadChange " + n + " " + m + " " + a);

        name.setValue(n);
        isMan.setValue(m);
        age.setValue(a);
    }

}
