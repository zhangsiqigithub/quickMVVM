package com.scientist.quickmvvm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewCityAdapter extends RecyclerView.Adapter<RecyclerViewCityAdapter.ViewHolder> {


    private List<City> mList = new ArrayList<>();

    private CityDifferItemCallback mItemCallback = new CityDifferItemCallback();


    @SuppressLint("CheckResult")
    public void setData(List<City> data) {
        List<City> list = new ArrayList<>(data);
        Observable.just(1).map(integer -> DiffUtil.calculateDiff(new Callback(list), true))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    diffResult.dispatchUpdatesTo(RecyclerViewCityAdapter.this);
                    mList = list;
                });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        City city = mList.get(i);
        viewHolder.mName.setText(city.getName());
        viewHolder.mPinyin.setText(city.getPinyin());
        viewHolder.mCode.setText(String.valueOf(city.getCode()));
        viewHolder.mPop.setText("人口：" + city.getPopulation());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.mPop.setText("人口：" + bundle.getInt(CityDifferItemCallback.key_1));
            holder.mName.setText(bundle.getString(CityDifferItemCallback.key_2));
            holder.mPinyin.setText(bundle.getString(CityDifferItemCallback.key_3));
            Log.d("MyTag", "bind holder with payloads");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mCode;
        private TextView mPinyin;
        private TextView mPop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name);
            mCode = itemView.findViewById(R.id.tv_code);
            mPinyin = itemView.findViewById(R.id.tv_pinyin);
            mPop = itemView.findViewById(R.id.tv_pop);
        }
    }

    class Callback extends DiffUtil.Callback {

        private List<City> mNewList;

        public Callback(List<City> newList) {
            this.mNewList = newList;
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
            City oldOne = mList.get(i);
            City newOne = mNewList.get(i1);

            return mItemCallback.areItemsTheSame(oldOne, newOne);
        }

        @Override
        public boolean areContentsTheSame(int i, int i1) {
            City oldOne = mList.get(i);
            City newOne = mNewList.get(i1);
            return mItemCallback.areContentsTheSame(oldOne, newOne);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return mItemCallback.getChangePayload(mList.get(oldItemPosition), mNewList.get(newItemPosition));
        }
    }

    static class CityDifferItemCallback extends DiffUtil.ItemCallback<City> {

        static final String key_1 = "1";
        static final String key_2 = "2";
        static final String key_3 = "3";

        @Override
        public boolean areItemsTheSame(@NonNull City city, @NonNull City t1) {
            int c1 = city.getCode();
            int c2 = t1.getCode();
            boolean same = c1 == c2;
            Log.d("MyTag", "areItemsTheSame old:" + city + " new:" + t1 + " same " + same);
            return same;
        }

        @Override
        public boolean areContentsTheSame(@NonNull City city, @NonNull City t1) {
            String name1 = city.getName();
            String name2 = t1.getName();

            String pinyin1 = city.getPinyin();
            String pinyin2 = t1.getPinyin();

            int pop1 = city.getPopulation();
            int pop2 = t1.getPopulation();
            boolean same = name1.equals(name2) && pinyin1.equals(pinyin2) && pop1 == pop2;

            Log.d("MyTag", "areContentsTheSame old:" + city + " new:" + t1 + " same " + same);
            return same;
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull City oldItem, @NonNull City newItem) {
            Bundle bundle = new Bundle();
            if (oldItem.getPopulation() != newItem.getPopulation()) {
                bundle.putInt(key_1, newItem.getPopulation());
            }
            if (!oldItem.getName().equals(newItem.getName())) {
                bundle.putString(key_2, newItem.getName());
            }
            if (!oldItem.getPinyin().equals(newItem.getPinyin())) {
                bundle.putString(key_3, newItem.getPinyin());
            }

            if (bundle.isEmpty())
                return null;

            return bundle;
        }
    }
}
