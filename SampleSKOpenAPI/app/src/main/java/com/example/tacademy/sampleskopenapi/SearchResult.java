package com.example.tacademy.sampleskopenapi;

import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-04-28.
 */

public class SearchResult {

   public ProductSearchResponse productSearchResponse;

    public SearchResult(JSONObject js){
        if(js == null) return;
        JSONObject jpsr = js.optJSONObject("ProductSearchResponse");
        productSearchResponse  = new ProductSearchResponse(jpsr);

    }
}
