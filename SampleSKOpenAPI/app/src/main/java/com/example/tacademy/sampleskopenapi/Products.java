package com.example.tacademy.sampleskopenapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class Products {
    public int totalCount;
    public ArrayList<Product> product = new ArrayList<Product>();

    public Products(JSONObject jsonObject){
        totalCount = jsonObject.optInt("TotalCount");
        JSONArray jproductarray = jsonObject.optJSONArray("Product");
        if(jproductarray == null) return;
        for(int i=0; i< jproductarray.length(); i++){
            JSONObject jproduct = jproductarray.optJSONObject(i);

            product.add(new Product(jproduct));
        }

    }
}
