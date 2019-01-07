package com.scientist.quickmvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class RecyclerViewLiveDataAdapter extends RecyclerView.Adapter<RecyclerViewLiveDataAdapter.ViewHolder> {

    private LiveDataList<ItemLiveData> mData;

    public RecyclerViewLiveDataAdapter(LiveDataList<ItemLiveData> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                mData.get(i).getLayoutId(), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ItemLiveData item = mData.get(i);
        viewHolder.mBinding.setVariable(item.getVariableId(), item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        public ViewHolder (ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
