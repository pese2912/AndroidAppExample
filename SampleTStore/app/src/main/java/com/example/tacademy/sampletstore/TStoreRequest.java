package com.example.tacademy.sampletstore;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tacademy on 2016-04-28.
 */
public class TStoreRequest extends NetworkRequest<TStore> {

    public static final String ORDER_MATCH = "R";
    public static final  String ORDER_RECENT = "L";
    public static final  String ORDER_DOWNLOAD = "D";
    String urlText;
    private static final String SEARCH_URL = "http://apis.skplanetx.com/tstore/products?count=20&order=L&page=1&searchKeyword=%s&version=1";
    public TStoreRequest(String keyworld){
        this(keyworld,1,20,ORDER_RECENT);
    }
    @Override
    URL getURL() throws MalformedURLException {
        return null;
    }
    public TStoreRequest(String keyworld, int page, int count){
        this(keyworld,page,count, ORDER_RECENT);

    }
    public TStoreRequest(String keyworld, int page, int count, String order){

        try {
             urlText = String.format(SEARCH_URL, URLEncoder.encode(keyworld,"utf-8"),page,count,order);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRequestHeaders(HttpURLConnection conn) {
        super.setRequestHeaders(conn);
        conn.setRequestProperty("Accept","application/json");
        // conn.setRequestProperty("Accept","application/xml");
        conn.setRequestProperty("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a");
    }

    @Override
    TStore parse(InputStream is) {
        Gson gson = new Gson();
        InputStreamReader isr = new InputStreamReader(is);
        SearchResult sr = gson.fromJson(isr, SearchResult.class);
        return sr.tStore;
    }
}
