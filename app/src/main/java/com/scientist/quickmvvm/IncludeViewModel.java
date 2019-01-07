package com.scientist.quickmvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class IncludeViewModel extends ViewModel {

    public MutableLiveData<String> item1 = new MutableLiveData<>();
    public MutableLiveData<Integer> item2 = new MutableLiveData<>();


    public IncludeViewModel() {
        item1.setValue("1111");
        item2.setValue(200);
    }
}
