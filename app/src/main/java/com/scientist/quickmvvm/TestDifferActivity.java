package com.scientist.quickmvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.scientist.quickmvvm.databinding.ActivityTestDifferBinding;
import com.scientist.quickmvvm.differ.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/4
 * Time: 11:35 AM
 * Desc:
 */
public class TestDifferActivity extends AppCompatActivity {



    private TestDifferViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new TestDifferViewModel();
        ActivityTestDifferBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test_differ);
        binding.setModel(mViewModel);
        binding.setLifecycleOwner(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }

    private Random mRandom = new Random();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                mViewModel.mData.add(new PersonItemViewModel(
                        new Person("新来的", mRandom.nextBoolean(), mRandom.nextInt(100), String.valueOf(mRandom.nextInt(30)))));
                return true;
            case R.id.menu_add_multi:
                List<PersonItemViewModel> multi = new ArrayList<>();
                multi.add(new PersonItemViewModel(
                        new Person("新来的1",
                                mRandom.nextBoolean(),
                                mRandom.nextInt(100),
                                String.valueOf(mRandom.nextInt(30)))));
                multi.add(new PersonItemViewModel(
                        new Person("新来的2",
                                mRandom.nextBoolean(),
                                mRandom.nextInt(100),
                                String.valueOf(mRandom.nextInt(30)))));
                multi.add(new PersonItemViewModel(
                        new Person("新来的3",
                                mRandom.nextBoolean(),
                                mRandom.nextInt(100),
                                String.valueOf(mRandom.nextInt(30)))));
                mViewModel.mData.addAll(multi);
                return true;
            case R.id.menu_remove:
                mViewModel.mData.remove(mRandom.nextInt(mViewModel.mData.size() - 1));
                return true;
            case R.id.menu_clear:
                mViewModel.mData.clear();
                return true;
            case R.id.menu_update:
                PersonItemViewModel viewModel = (PersonItemViewModel) mViewModel.mData.get(0);
                viewModel.getData().setId("00000");
                return true;
            case R.id.menu_update_1:
                PersonItemViewModel origin = (PersonItemViewModel) mViewModel.mData.get(0);
                PersonItemViewModel viewModel1 = new PersonItemViewModel(new Person("update", true, mRandom.nextInt(), origin.getData().getId()));
                mViewModel.mData.set(0, viewModel1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
