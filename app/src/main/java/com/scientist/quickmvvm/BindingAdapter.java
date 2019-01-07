package com.scientist.quickmvvm;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scientist.lib.ItemViewModel;
import com.scientist.lib.ItemViewModelDifferAdapter;

import java.util.List;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/4
 * Time: 11:45 AM
 * Desc:
 */
public class BindingAdapter {

    @android.databinding.BindingAdapter("data")
    public static void bindRecyclerView(RecyclerView view, LiveDataList<ItemViewModel> data) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ItemViewModelDifferAdapter adapter = new ItemViewModelDifferAdapter();
        data.observe(((AppCompatActivity) view.getContext()), new Observer<List<ItemViewModel>>() {
            @Override
            public void onChanged(@Nullable List<ItemViewModel> itemViewModels) {
                adapter.setData(itemViewModels);
            }
        });
        view.setAdapter(adapter);

    }
}
