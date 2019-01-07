package com.scientist.quickmvvm;

import android.arch.lifecycle.LiveData;

public abstract class ItemLiveData extends LiveData {

    public abstract int getLayoutId();

    public abstract int getVariableId();
}
