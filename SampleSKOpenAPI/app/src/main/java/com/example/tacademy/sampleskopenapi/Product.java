package com.example.tacademy.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class Product {
    public int productCode;
    public String productName;
    public int productPrice;
    public String productImage;

    public  Product(JSONObject jsonObject){
        if(jsonObject == null) return;
        productCode = jsonObject.optInt("ProducCode");
        productName = jsonObject.optString("ProductName");
        productPrice = jsonObject.optInt("ProductPrice");
        productImage  = jsonObject.optString("ProductImage");
    }
  public String toString(){
       return productName;
   }


}
