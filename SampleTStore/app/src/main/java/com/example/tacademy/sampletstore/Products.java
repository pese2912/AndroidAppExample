package com.example.tacademy.sampletstore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class Products {
    @com.begentgroup.xmlparser.SerializedName("product")
    @SerializedName("product")
    ArrayList<Product> productList;
}