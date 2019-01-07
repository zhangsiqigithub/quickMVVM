package com.scientist.quickmvvm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM " + Product.TABLE_NAME)
    LiveData<List<Product>> getProducts();


    @Query("SELECT * FROM " + Product.TABLE_NAME)
    List<Product> getProductsList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Delete
    void delete(Product...products);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Product product);
}
