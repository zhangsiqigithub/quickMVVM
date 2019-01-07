package com.scientist.quickmvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.scientist.quickmvvm.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SecondActivityViewModel viewModel = ViewModelProviders.of(this).get(SecondActivityViewModel.class);

        ActivitySecondBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }

    public void onclickButton(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
