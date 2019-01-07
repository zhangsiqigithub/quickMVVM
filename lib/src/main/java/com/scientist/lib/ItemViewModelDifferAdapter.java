package com.scientist.lib;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/3
 * Time: 下午3:32
 * Desc: DataBinding 通用 Adapter
 */
public class ItemViewModelDifferAdapter extends RecyclerView.Adapter<ItemViewModelDifferAdapter.ViewHolder> {


    private List<ItemViewModel> mList = new ArrayList<>();


    @SuppressLint("CheckResult")
    public void setData(List<ItemViewModel> data) {
        List<ItemViewModel> list = new ArrayList<>(data);
        Observable.just(1).map(integer -> DiffUtil.calculateDiff(new Callback(list), true))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    diffResult.dispatchUpdatesTo(ItemViewModelDifferAdapter.this);
                    mList = list;
                });
    }


    @Override
    public int getItemViewType(int position) {
        ItemViewModel viewModel = mList.get(position);
        return viewModel.getLayoutId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), viewType, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ItemViewModel viewModel = mList.get(position);
        viewHolder.mBinding.setVariable(viewModel.getVariableId(), viewModel);
        viewHolder.mBinding.executePendingBindings();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            ItemViewModel viewModel = mList.get(position);
            viewModel.onPayloadChange((HashMap<String, Object>) payloads.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    class Callback extends DiffUtil.Callback{

        private List<ItemViewModel> mNewList;
        public Callback(List<ItemViewModel> newList) {
            mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int i, int i1) {
            ItemViewModel oldItem = mList.get(i);
            ItemViewModel newItem = mNewList.get(i1);

            if (oldItem != null && newItem != null) {

                if (oldItem.getClass() != newItem.getClass() || oldItem.getData().getClass() != newItem.getData().getClass()) {
                    throw new IllegalStateException("data error");
                }

                String className = oldItem.getData().getClass().getName();
                Differ d = DifferManager.getInstance().get(className);
                if (d == null) {
                    throw new IllegalStateException("class " + className + " need a differ !");
                }

                return d.areItemsTheSame(oldItem, newItem);
            }
            return oldItem == null && newItem == null;
        }

        @Override
        public boolean areContentsTheSame(int i, int i1) {
            ItemViewModel oldItem = mList.get(i);
            ItemViewModel newItem = mNewList.get(i1);

            if (oldItem == null && newItem == null)
                return true;
            if (oldItem != null && newItem != null) {
                if (oldItem.getClass() != newItem.getClass() || oldItem.getData().getClass() != newItem.getData().getClass()) {
                    throw new IllegalStateException("data error");
                }
                String className = oldItem.getData().getClass().getName();
                Differ d = DifferManager.getInstance().get(className);
                if (d == null) {
                    throw new IllegalStateException("class " + className + " need a differ !");
                }
                return d.areContentsTheSame(oldItem, newItem);

            }
            throw new AssertionError();
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            ItemViewModel oldItem = mList.get(oldItemPosition);
            ItemViewModel newItem = mNewList.get(newItemPosition);
            Differ d = DifferManager.getInstance().get(oldItem.getData().getClass().getName());
            return d.getChangePayload(oldItem, newItem);
        }
    }
}
