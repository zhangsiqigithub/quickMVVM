package com.scientist.quickmvvm;

import android.arch.core.util.Function;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.AndroidException;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SecondActivityViewModel extends ViewModel {

    public MutableLiveData<String> name;
    public MutableLiveData<Integer> age;

    public MutableLiveData<String> address;

    public IncludeViewModel mItem;


    public SecondActivityViewModel() {
        name = new MutableLiveData<>();
        name.setValue("zhangsan");

        age = new MutableLiveData<>();
        age.setValue(21);

        address = new MutableLiveData<>();
        address.setValue("北京密云");

        mItem = new IncludeViewModel();


        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .doOnNext(aLong -> {
                    int a = age.getValue();
                    a ++;
                    age.postValue(a);

                    String na = name.getValue() + a;
                    name.postValue(na);

                    mItem.item2.postValue(a);

                })
                .subscribe();

    }
}
