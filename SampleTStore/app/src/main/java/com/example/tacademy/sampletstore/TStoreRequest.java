package com.example.tacademy.sampletstore;

import com.begentgroup.xmlparser.XMLParser;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class TStoreRequest extends NetworkRequest<TStore> {
    private static final String SEARCH_URL = "http://apis.skplanetx.com/tstore/products?searchKeyword=%s&page=%s&count=%s&order=%s&version=1";

    public static final String ORDER_MATCH = "R";
    public static final String ORDER_RECENT = "L";
    public static final String ORDER_DOWNLOAD = "D";
    String urlText;
    public TStoreRequest(String keyword){
        this(keyword, 1 , 20, ORDER_RECENT);
    }

    public TStoreRequest(String keyword, int page, int count) {
        this(keyword, page, count, ORDER_RECENT);
    }

    public TStoreRequest(String keyword, int page, int count, String order) {
        try {
            urlText = String.format(SEARCH_URL, URLEncoder.encode(keyword,"utf-8"), page, count, order);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    URL getURL() throws MalformedURLException {
        return new URL(urlText);
    }

    @Override
    public void setRequestHeaders(HttpURLConnection conn) {
        super.setRequestHeaders(conn);
        conn.setRequestProperty("Accept","application/xml");
        conn.setRequestProperty("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a");
    }

    @Override
    TStore parse(InputStream is) {
        XMLParser parser = new XMLParser();
        TStore tstore = parser.fromXml(is, "tstore", TStore.class);
        return tstore;
    }
}