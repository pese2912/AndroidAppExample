package com.example.tacademy.sampletstore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    ArrayAdapter<Product> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keywordView = (EditText)findViewById(R.id.edit_input);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordView.getText().toString();
                if (!TextUtils.isEmpty(keyword)) {
                    new SearchTask().execute(keyword);
                }
            }
        });
    }

    private static final String SEARCH_URL = "http://apis.skplanetx.com/tstore/products?count=20&order=L&page=1&searchKeyword=%s&version=1";

    class SearchTask extends AsyncTask<String, Integer, TStore> {
        @Override
        protected TStore doInBackground(String... params) {
            String keyword = params[0];
            try {
                String urlText = String.format(SEARCH_URL, URLEncoder.encode(keyword,"utf-8"));
                URL url = new URL(urlText);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                conn.setRequestProperty("Accept","application/json");
                conn.setRequestProperty("Accept","application/xml");
                conn.setRequestProperty("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a");
                int code = conn.getResponseCode();

                if (code >= 200 && code < 300) {
                    Gson gson = new Gson();
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                    SearchResult sr = gson.fromJson(isr, SearchResult.class);
                   return sr.tStore;
                    //XMLParser parser = new XMLParser();
                    //TStore tstore = parser.fromXml(conn.getInputStream(), "tstore", TStore.class);
                    //return tstore;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(TStore tStore) {
            super.onPostExecute(tStore);
            if (tStore != null) {
                for (Product p : tStore.products.productList) {
                    mAdapter.add(p);
                }
            }
        }
    }
}