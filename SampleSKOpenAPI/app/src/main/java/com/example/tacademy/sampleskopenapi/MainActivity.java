package com.example.tacademy.sampleskopenapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    ArrayAdapter<Product> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edit_keyword);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                if(!TextUtils.isEmpty(keyword)){
                    new MySearch().execute(keyword); //작업 쓰레드 시작
                }

            }
        });
    }

    private static final String SEARCH_URL="http://apis.skplanetx.com/11st/common/products?searchKeyword=%s&version=1";

    class MySearch extends AsyncTask<String, Integer, SearchResult>{

        @Override
        protected SearchResult doInBackground(String... params) { //백그라운드
            String keyword = params[0]; //키값 받음
            try {
                String urlText = String.format(SEARCH_URL, URLEncoder.encode(keyword,"utf-8")); //utf8로 인코딩
                URL url = null;
                try {
                    url = new URL(urlText); //url 생성
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //url 접속
                    conn.setRequestProperty("Accept","application/json"); //보낼 속성값
                    conn.setRequestProperty("appKey","ec449f14-3190-30de-9143-75e1be5e7521");
                    int code = conn.getResponseCode(); // 응답코드 받음

                    if(code >=200 && code<300){ //성공 시
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  //인풋스트림으로 값 받기
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine())!=null){ //없을 때 까지 받아옴
                            sb.append(line).append("\n\r");

                        }

                        String s = sb.toString();
                        JSONObject jobject = new JSONObject(s); //받은 값 JSON으로 받고
                        SearchResult sr = new SearchResult(jobject); // 객체로 넘겨주기

                        return sr;
                     //   return sb.toString();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(SearchResult s) {
            super.onPostExecute(s);
            if(s!=null){
                //Toast.makeText(MainActivity.this, "RESULT : "+ s.toString(), Toast.LENGTH_SHORT).show();
                for (Product p : s.productSearchResponse.products.product){ //하나씩 리스트뷰에 담기
                    mAdapter.add(p);

                }
            }
        }
    }
}
