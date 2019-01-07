package com.scientist.quickmvvm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/7
 * Time: 4:07 PM
 * Desc:
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {



    private AsyncListDiffer<City> mDiffer = new AsyncListDiffer<>(this, new CityDifferItemCallback());


    @SuppressLint("CheckResult")
    public void setData(List<City> data) {
        List<City> list = new ArrayList<>(data);
        mDiffer.submitList(list);

    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CityAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder viewHolder, int i) {
        City city = mDiffer.getCurrentList().get(i);
        viewHolder.mName.setText(city.getName());
        viewHolder.mPinyin.setText(city.getPinyin());
        viewHolder.mCode.setText(String.valueOf(city.getCode()));
        viewHolder.mPop.setText("人口：" + city.getPopulation());
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.mPop.setText("人口：" + bundle.getInt(CityAdapter.CityDifferItemCallback.key_1));
            holder.mName.setText(bundle.getString(CityAdapter.CityDifferItemCallback.key_2));
            holder.mPinyin.setText(bundle.getString(CityAdapter.CityDifferItemCallback.key_3));
            Log.d("MyTag", "bind holder with payloads");
        }
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
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

            Log.d("MyTag", "getChangePayload");
            return bundle;
        }
    }
}
