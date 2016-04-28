package com.example.tacademy.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class ProductSearchResponse { //데이터를 담을 클래스 정의
    public Request request;
    public Products products;
    public ProductSearchResponse(JSONObject jsonObject){
        if(jsonObject == null) return;
        JSONObject jrequest = jsonObject.optJSONObject("Request");
        JSONObject jproducts = jsonObject.optJSONObject("Products");
        request = new Request(jrequest);
        products = new Products(jproducts);
    }
}
