package com.scientist.quickmvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity {


    private CityAdapter mAdapter;
    private List<City> mData = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new CityAdapter();

        recyclerView.setAdapter(mAdapter);

        mData.add(new City("北京", "beijing", 1, 1000000));
//        mData.add(new City("河北", "hebei", 2, 90000));
//        mData.add(new City("重庆", "chongqing", 3, 324923));
//        mData.add(new City("天津", "tianjin", 4, 88888));
//        mData.add(new City("西安", "xian", 5, 77777));
//        mData.add(new City("淮安", "hubei", 6, 9766));
//        mData.add(new City("湖北", "hubei", 7, 77666));
        mAdapter.setData(mData);
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
                mData.add(new City("add ", "add", 8, 888999));
                mAdapter.setData(mData);
                return true;
            case R.id.menu_add_multi:
                mData.add(new City("add1 ", "add1", 12, 888991));
                mData.add(new City("add2 ", "add2", 13, 888992));
                mData.add(new City("add3 ", "add3", 14, 888993));
                mAdapter.setData(mData);
                return true;
            case R.id.menu_remove:
                mData.remove(mRandom.nextInt(mData.size()));
                mAdapter.setData(mData);
                return true;
            case R.id.menu_clear:
                mData.clear();
                mAdapter.setData(mData);
                return true;
            case R.id.menu_update:
                sopIterable(mData);
                City city = mData.get(0);
                city.setPopulation(mRandom.nextInt(10));
//                city.setCode(mRandom.nextInt(20));
                city.setName("update");
                sopIterable(mData);
                mAdapter.setData(mData);
                return true;
            case R.id.menu_update_1:
                sopIterable(mData);
                City org = mData.get(0);
                mData.set(0, new City( "name" + mRandom.nextInt(10),  "pinyin" + mRandom.nextInt(10), org.getCode(), mRandom.nextInt(100000)));
                sopIterable(mData);
                mAdapter.setData(mData);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void sopIterable(Iterable<?> iterable) {
        Log.d("MyTag", "-----------start----------");
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Log.d("MyTag", iterator.next().toString());
        }
        Log.d("MyTag", "-----------end------------");
    }
}
