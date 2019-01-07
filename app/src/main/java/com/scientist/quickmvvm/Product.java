package com.scientist.quickmvvm;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = Product.TABLE_NAME)
public class Product {

    public static final String TABLE_NAME = "product";

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
