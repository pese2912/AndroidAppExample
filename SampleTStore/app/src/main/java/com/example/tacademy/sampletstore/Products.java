package com.example.tacademy.sampletstore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class Products {

    @SerializedName("product") // product를  productList로 지정
    ArrayList<Product> productList;

}
